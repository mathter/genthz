/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2021 mathter@mail.ru
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
package org.genthz.context.def;

import org.genthz.ObjectFactory;
import org.genthz.context.Accessor;
import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.IndexedContext;

public abstract class AbstractIndexedContext<T> extends AbstractContext<T> implements IndexedContext<T>, Accessor<T> {
    private final int index;

    private final Class<T> clazz;

    public AbstractIndexedContext(ObjectFactory objectFactory, Context<?> parent, int index, Class<T> clazz) {
        this(objectFactory, parent, null, index, clazz);
    }

    public AbstractIndexedContext(ObjectFactory objectFactory, Context<?> parent, Bindings bindings, int index, Class<T> clazz) {
        super(objectFactory, parent, bindings);
        this.index = index;
        this.clazz = clazz;
    }

    @Override
    public Class<T> clazz() {
        return this.clazz;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public int index() {
        return this.index;
    }

    @Override
    public T node() {
        return this.get();
    }
}
