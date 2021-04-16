/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;
import org.genthz.loly.context.RefFieldContext;
import org.genthz.loly.context.Stage;
import org.genthz.loly.context.ValFieldContext;
import org.genthz.loly.context.ValueContext;
import org.genthz.loly.reflect.Accessor;
import org.genthz.util.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

class DefaultFiller<T> implements Filler<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFiller.class);

    private final Collection<String> includedFieldNames;

    private final Collection<String> excludedFieldNames;

    private final boolean skipStaticFields = true;

    private final boolean skipFinalFields = true;

    public DefaultFiller(
            Collection<String> includedFieldNames,
            Collection<String> excludedFieldNames
    ) {
        this.includedFieldNames = includedFieldNames;
        this.excludedFieldNames = excludedFieldNames;
    }

    @Override
    public T apply(Context<T> context, T object) {
        this.classes(context.clazz())
                .flatMap(e -> accessors((ValueContext<T>) context, e))
                .forEach(this::fill);

        return object;
    }

    private <T> void fill(Accessor<T> accessor) {
        final ValueContext<T> context = (ValueContext<T>) accessor;

        if (context.getStage() != Stage.COMPLETE) {
            final InstanceBuilder<T> instanceBuilder = context.objectFactory().instanceBuilder(context);
            final Filler<T> filler = context.objectFactory().filler(context);
            final T object = instanceBuilder.apply(context);

            accessor.setInstance(object);
            accessor.setFilled(filler.apply(context, object));
        }
    }

    private Stream<Accessor<?>> accessors(ValueContext<T> parent, Class<?> clazz) {
        return this.fieldAccessors(parent, clazz);
    }

    private <T> Stream<Accessor<?>> fieldAccessors(ValueContext<T> parent, Class<?> clazz) {
        return java.util.stream.Stream
                .of(clazz.getDeclaredFields())
                .filter(f -> {
                            boolean b = Optional
                                    .of(f)
                                    .map(Field::getName)
                                    .map(e -> (this.includedFieldNames == null && this.excludedFieldNames == null)
                                            || (this.includedFieldNames != null && this.includedFieldNames.contains(e))
                                            || (this.excludedFieldNames != null && !this.excludedFieldNames.contains(e))
                                    )
                                    .get()
                                    && !f.isEnumConstant()
                                    && !Optional.of(f.getModifiers()).map(m -> Modifier.isStatic(m) || Modifier.isFinal(m)).get();
                            return b;
                        }
                )
                .flatMap(f -> {
                            final Stream<Accessor<?>> result;
                            final Class<?> c = f.getType();

                            if (c.isPrimitive()) {
                                result = Stream.of(new ValFieldContext(parent, f));
                            } else if (Collection.class.isAssignableFrom(c)) {
                                result = Stream.of(new RefFieldContext(parent, f));
                            } else {
                                result = Stream.of(new RefFieldContext(parent, f));
                            }

                            return result;
                        }
                );
    }

    private Stream<Class<?>> classes(Class<?> clazz) {
        return StreamUtil.of(clazz, Class::getSuperclass);
    }
}
