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
import org.genthz.util.Util;

import java.lang.reflect.Field;
import java.util.Objects;

class FieldContext<T> extends AbtractContext<T> {
    private final Field field;

    public FieldContext(ObjectFactory objectFactory, Context<?> parent, Field field) {
        super(Bindings.bindings(Objects.requireNonNull(parent.bindings())), objectFactory, Objects.requireNonNull(parent));
        this.field = field;
    }

    @Override
    public Class<T> clazz() {
        return (Class<T>) this.field.getType();
    }

    @Override
    public T value() {
        return Util.getFieldValue(this.field, this.parent().value());
    }

    @Override
    public String name() {
        return this.field.getName();
    }

    @Override
    public void setInstance(T value) {
        super.setInstance(value);
        Util.setFieldValue(this.field, parent().value(), value);
    }

    @Override
    public void setFilled(T value) {
        super.setFilled(value);
        Util.setFieldValue(this.field, parent().value(), value);
    }
}
