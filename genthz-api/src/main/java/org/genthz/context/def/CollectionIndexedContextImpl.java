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
import org.genthz.context.CollectionIndexedContext;
import org.genthz.context.Context;

import java.util.function.BiConsumer;

public class CollectionIndexedContextImpl<T> extends AbstractIndexedContext<T> implements CollectionIndexedContext<T> {
    private final BiConsumer<T, Integer> setter;

    public CollectionIndexedContextImpl(ObjectFactory objectFactory,
                                        Context<?> parent,
                                        int index,
                                        Class<T> clazz,
                                        BiConsumer<T, Integer> setter
    ) {
        super(objectFactory, parent, index, clazz);
        this.setter = setter;
    }

    public CollectionIndexedContextImpl(ObjectFactory objectFactory,
                                        Context<?> parent,
                                        Bindings bindings,
                                        int index,
                                        Class<T> clazz,
                                        BiConsumer<T, Integer> setter
    ) {
        super(objectFactory, parent, bindings, index, clazz);
        this.setter = setter;
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setInstance(T value) {
        super.setInstance(value);
        this.setter.accept(value, this.index());
    }

    @Override
    public void setFilled(T value) {
        super.setFilled(value);
        this.setter.accept(value, this.index());
    }
}
