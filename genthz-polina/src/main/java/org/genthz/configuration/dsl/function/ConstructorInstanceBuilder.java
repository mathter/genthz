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
package org.genthz.configuration.dsl.function;

import org.genthz.context.context.Bindings;
import org.genthz.context.context.Context;
import org.genthz.ObjectFactory;
import org.genthz.context.context.Stage;
import org.genthz.configuration.InstanceBuilder;
import org.genthz.context.Accessor;
import org.genthz.context.ConstructorArgumentContext;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ConstructorInstanceBuilder<T> implements InstanceBuilder<T> {

    public abstract Constructor<T> constructor(Context<T> context);

    @Override
    public T apply(Context<T> context) {
        final T result;
        final Class<T> clazz = context.clazz();
        final Constructor<T> constructor = this.constructor(context);
        final Object[] parameters = this.parameters(context, constructor);

        try {
            result = constructor.newInstance(parameters);
        } catch (Throwable e) {
            throw new IllegalStateException("Can't create instance of class=" + clazz + " for context=" + context + "!", e);
        }

        return result;
    }

    protected static <T> Object[] parameters(Context<T> context, Constructor<T> constructor) {
        final ObjectFactory objectFactory = context.objectFactory();
        final ConstructorArgumentContext<?>[] contexts = ConstructorInstanceBuilder.constructorArgumentContexts(context, constructor);
        final Object[] objects = new Object[contexts.length];

        for (int i = 0; i < objects.length; i++) {
            objects[i] = objectFactory.build(contexts[i]);
        }

        return objects;
    }

    protected static <T> Constructor<T> constructor(Predicate<Constructor> predicate, Context<T> context) {
        final Constructor<T> constructor;
        final Class<T> clazz = context.clazz();
        final List<Constructor> candidates = Arrays.stream(clazz.getConstructors())
                .filter(e -> predicate.test(e))
                .collect(Collectors.toList());

        if (candidates.size() == 0) {
            throw new IllegalStateException("There is no constructor in class=" + clazz + " for " + context + "!");
        } else if (candidates.size() > 1) {
            throw new IllegalStateException("There are more then one constructor in class=" + clazz + " for " + context + "!");
        } else {
            constructor = candidates.get(0);
        }

        return constructor;
    }

    private static <T> ConstructorArgumentContext<?>[] constructorArgumentContexts(Context<T> context, Constructor<T> constructor) {
        final Class<?>[] parameterClasses = constructor.getParameterTypes();
        final ConstructorArgumentContext<?>[] result = new ConstructorArgumentContext[parameterClasses.length];

        for (int i = 0; i < parameterClasses.length; i++) {
            result[i] = new ConstructorArgumentContextImpl(context, parameterClasses[i], i);
        }

        return result;
    }

    private static class ConstructorArgumentContextImpl<T> implements ConstructorArgumentContext<T>, Accessor<T> {
        private final Context<?> parent;

        private final Class<T> clazz;

        private final int index;

        private Stage stage = Stage.NEW;

        private T value;

        public ConstructorArgumentContextImpl(Context parent, Class<T> clazz, int index) {
            this.parent = parent;
            this.clazz = clazz;
            this.index = index;
        }

        @Override
        public Class<T> clazz() {
            return this.clazz;
        }

        @Override
        public T value() {
            return this.value;
        }

        @Override
        public int index() {
            return this.index;
        }

        @Override
        public Bindings bindings() {
            return this.parent.bindings();
        }

        @Override
        public Context<?> parent() {
            return this.parent;
        }

        @Override
        public ObjectFactory objectFactory() {
            return this.parent.objectFactory();
        }

        @Override
        public Stage stage() {
            return this.stage;
        }

        @Override
        public void stage(Stage stage) {
            this.stage = stage;
        }

        @Override
        public void setInstance(T value) {
            Accessor.super.setInstance(value);
            this.value = value;
        }

        @Override
        public void setFilled(T value) {
            Accessor.super.setFilled(value);
            this.value = value;
        }
    }
}
