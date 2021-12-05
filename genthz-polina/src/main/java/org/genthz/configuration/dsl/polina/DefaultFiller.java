/*
 * GenThz - testing becomes easier
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
package org.genthz.configuration.dsl.polina;

import org.genthz.ObjectFactory;
import org.genthz.configuration.Filler;
import org.genthz.configuration.dsl.Defaults;
import org.genthz.context.context.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

class DefaultFiller<T> implements Filler<T> {
    private final Predicate<Field> NON_FINAL_FIELD_PREDICATE = f -> !Modifier.isFinal(f.getModifiers());

    private final Predicate<Field> NON_STATIC_FIELD_PREDICATE = f -> !Modifier.isStatic(f.getModifiers());

    private final Defaults defaults;

    public DefaultFiller(Defaults defaults) {
        this.defaults = defaults;
    }

    @Override
    public T apply(Context<T> context, T value) {
        final ObjectFactory objectFactory = context.objectFactory();

        if (context.length() < this.defaults.defaultDeep().apply(context)) {
            this.fieldStream(context.clazz())
                    .map(f -> new FieldContext<>(objectFactory, context, f))
                    .forEach(ctx -> {
                        objectFactory.build(ctx);
                    });
        }
        return value;
    }

    private <T> Stream<Field> fieldStream(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(NON_FINAL_FIELD_PREDICATE)
                .filter(NON_STATIC_FIELD_PREDICATE);
    }
}
