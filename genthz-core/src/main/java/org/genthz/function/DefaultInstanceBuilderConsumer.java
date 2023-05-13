/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.function;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.ConstructorChoiceStrategy;
import org.genthz.Defaults;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.MinimalArgCountConstructorChoiceStrategy;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Objects;

public class DefaultInstanceBuilderConsumer<T> implements InstanceBuilderConsumer<T> {
    private final ConstructorChoiceStrategy constructorChoiceStrategy;

    public DefaultInstanceBuilderConsumer() {
        this(new MinimalArgCountConstructorChoiceStrategy());
    }


    public DefaultInstanceBuilderConsumer(ConstructorChoiceStrategy constructorChoiceStrategy) {
        this.constructorChoiceStrategy = Objects.requireNonNull(constructorChoiceStrategy);
    }

    @Override
    public void instance(InstanceContext<T> context) {
        final T instance;
        final ContextFactory contextFactory = context.contextFactory();
        final ObjectFactory objectFactory = context.objectFactory();
        final Type type = context.type();

        final Constructor constructor = this.constructorChoiceStrategy.constructor(type);

        try {
            instance = (T) constructor.newInstance(contextFactory.byConstructor(context, constructor)
                    .stream()
                    .map(e -> objectFactory.process(e).instance())
                    .toArray(i -> new Object[i]));
        } catch (Throwable t) {
            throw new IllegalStateException(
                    String.format("Can't create object instance for context %s", context),
                    t
            );
        }

        context.set(instance);
    }
}
