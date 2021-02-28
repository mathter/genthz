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
package org.genthz;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Spec<T> implements Context<T> {
    private final Class<T> clazz;

    private final Bindings bindings;

    public static <T> Spec<T> of(Class<T> clazz) {
        return of(clazz, null);
    }

    public static <T> Spec<T> of(Class<T> clazz, Bindings bindings) {
        return new Spec(clazz, bindings);
    }

    public static <T, E> Spec<T> of(Class<T> clazz, Class<E> componentClazz, int size) {
        return Spec.of(clazz, componentClazz, size, null);
    }

    public static <T, E> Spec<T> of(Class<T> clazz, Class<E> componentClazz, int size, Bindings bindings) {
        return new SpecCollection(clazz, bindings, componentClazz, size);
    }

    private Spec(Class<T> clazz, Bindings bindings) {
        this.clazz = Objects.requireNonNull(clazz);
        this.bindings = bindings != null ? bindings : Bindings.bindings();
    }

    @Override
    public Bindings bindings() {
        return this.bindings;
    }

    @Override
    public Class<T> clazz() {
        return this.clazz;
    }

    @Override
    public T node() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String name() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Context<T> parent() {
        return null;
    }

    @Override
    public Collection<Context<?>> childs() {
        return Collections.emptyList();
    }

    @Override
    public ObjectFactory objectFactory() {
        throw new UnsupportedOperationException();
    }

    static class SpecCollection<T, E> extends Spec<T> {
        private final Class<E> componentClazz;

        private final int size;

        private SpecCollection(Class<T> clazz, Bindings bindings, Class<E> componentClazz, int size) {
            super(clazz, bindings);
            this.componentClazz = Objects.requireNonNull(componentClazz);
            this.size = size;
        }
    }
}