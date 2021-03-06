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
import org.genthz.Context;
import org.genthz.ObjectFactory;
import org.genthz.loly.reflect.Accessor;

import java.util.Optional;

public abstract class ValueContext<T> implements Context<T>, Accessor<T> {

    private final Optional<ValueContext<?>> parent;

    private final Bindings bindings;

    private final ObjectFactory objectFactory;

    private Stage stage = Stage.NEW;

    public ValueContext(ObjectFactory objectFactory, Bindings bindings) {
        this.parent = Optional.empty();
        this.bindings = bindings;
        this.objectFactory = objectFactory;
    }

    public ValueContext(ObjectFactory objectFactory, ValueContext<?> parent) {
        this.parent = Optional.ofNullable(parent);
        this.bindings = this.parent.map(Context::bindings).orElse(null);
        this.objectFactory = objectFactory;
    }

    @Override
    public Bindings bindings() {
        return this.bindings;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Optional<ValueContext<?>> parent() {
        return this.parent;
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.objectFactory;
    }

    protected StringBuilder info() {
        final StringBuilder sb = new StringBuilder();
        this.parent().ifPresent((c) -> sb.append(c.info()));
        sb.append("/{name=" + this.name()).append(", class=" + this.clazz()).append(", value=" + this.node()).append('}');


        return sb;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
                .append('[').append(this.info()).append(']')
                .toString();
    }
}


