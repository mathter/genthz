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

import org.genthz.context.context.Bindings;
import org.genthz.context.context.Context;
import org.genthz.ObjectFactory;
import org.genthz.context.ConstructorArgumentContext;

class ConstructorArgumentContextImpl<T> extends AbtractContext<T> implements ConstructorArgumentContext<T> {
    private final Class<T> clazz;

    private final int index;

    private T value;

    public ConstructorArgumentContextImpl(ObjectFactory objectFactory, Context parent, Class<T> clazz, int index) {
        super(Bindings.bindings(parent.bindings()), objectFactory, parent);

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
}
