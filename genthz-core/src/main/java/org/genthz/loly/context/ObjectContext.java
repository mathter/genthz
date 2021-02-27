/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter
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
package org.genthz.loly.context;

import org.genthz.Bindings;
import org.genthz.ObjectFactory;

import java.util.Objects;

public class ObjectContext<T> extends ComplexContext<T> implements org.genthz.ObjectContext<T> {

    private final Class<T> clazz;

    private T object;

    public ObjectContext(ObjectFactory objectFactory, Bindings bindings, Class<T> clazz) {
        super(objectFactory, bindings);
        this.clazz = Objects.requireNonNull(clazz);
    }

    @Override
    public Class<T> clazz() {
        return this.clazz;
    }

    @Override
    public T node() {
        return this.object;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public T get() {
        return this.object;
    }

    @Override
    public void setInstance(T value) {
        this.object = value;
        this.setStage(Stage.INITIALIZATION);
    }

    @Override
    public void setFilled(T value) {
        this.object = value;
        this.setStage(Stage.COMPLETE);
    }
}
