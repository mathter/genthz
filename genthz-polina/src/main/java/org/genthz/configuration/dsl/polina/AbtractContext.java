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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.genthz.context.context.Bindings;
import org.genthz.context.context.Context;
import org.genthz.ObjectFactory;
import org.genthz.context.context.Stage;
import org.genthz.context.Accessor;

abstract class AbtractContext<T> implements Context<T>, Accessor<T> {
    private final Bindings bindings;

    private final ObjectFactory objectFactory;

    private final Context<?> parent;

    private Stage stage = Stage.NEW;

    public AbtractContext(Bindings bindings, ObjectFactory objectFactory, Context<?> parent) {
        this.bindings = bindings;
        this.objectFactory = objectFactory;
        this.parent = parent;
    }

    @Override
    public Bindings bindings() {
        return bindings;
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
    public Context<?> parent() {
        return this.parent;
    }

    @Override
    public void stage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
    }
}
