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

class ObjectContext<T> extends AbtractContext<T> {
    private final Class<T> clazz;

    private T object;

    public ObjectContext(ObjectFactory objectFactory, Context<?> parent, Class<T> clazz, T object) {
        super(Bindings.bindings(parent != null ? parent.bindings() : null), objectFactory, parent);
        this.clazz = clazz;
        this.object = object;
    }

    @Override
    public Class<T> clazz() {
        return this.clazz;
    }

    @Override
    public T value() {
        return this.object;
    }

    @Override
    public String name() {
        return "/";
    }

    @Override
    public void setInstance(T value) {
        super.setInstance(value);
        this.object = value;
    }

    @Override
    public void setFilled(T value) {
        super.setFilled(value);
        this.object = value;
    }
}
