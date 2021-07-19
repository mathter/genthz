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
import org.genthz.context.Bindings;
import org.genthz.context.Context;

public class ConstructorIndexedContextImpl<T> extends AbstractIndexedContext<T> {
    private T value;

    public ConstructorIndexedContextImpl(ObjectFactory objectFactory, Context<?> parent, int index, Class<T> clazz) {
        super(objectFactory, parent, index, clazz);
    }

    public ConstructorIndexedContextImpl(ObjectFactory objectFactory, Context<?> parent, Bindings bindings, int index, Class<T> clazz) {
        super(objectFactory, parent, bindings, index, clazz);
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void setInstance(T value) {
        super.setInstance(value);
        this.value = value;
    }

    @Override
    public void setFilled(T value) {
        super.setFilled(value);
        this.value = value;
    }
}
