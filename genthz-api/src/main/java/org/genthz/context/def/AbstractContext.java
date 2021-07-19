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
import org.genthz.context.Stage;

import java.util.Optional;

public abstract class AbstractContext<T> implements Context<T>, Accessor<T> {
    private final ObjectFactory objectFactory;

    private final Bindings bindings;

    private final Optional<Context<?>> parent;

    private Stage stage = Stage.NEW;

    public AbstractContext(ObjectFactory objectFactory, Context<?> parent) {
        this(objectFactory, parent, null);
    }

    public AbstractContext(ObjectFactory objectFactory, Context<?> parent, Bindings bindings) {
        this.objectFactory = objectFactory;
        this.parent = Optional.ofNullable(parent);
        this.bindings = bindings != null
                ? bindings
                : this.parent.map(Context::bindings)
                .map(e -> Bindings.bindings(e))
                .orElse(Bindings.bindings());
    }

    @Override
    public Bindings bindings() {
        return this.bindings;
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.objectFactory;
    }

    @Override
    public Stage stage() {
        return this.stage;
    }

    @Override
    public void stage(Stage value) {
        this.stage = value;
    }

    @Override
    public Optional<? extends Context<?>> parent() {
        return this.parent;
    }

    @Override
    public void setInstance(T value) {
        this.stage = Stage.INSTANCE;
    }

    @Override
    public void setFilled(T value) {
        this.stage = Stage.COMPLETE;
    }
}
