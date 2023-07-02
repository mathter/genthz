/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.dasha.context;

import org.genthz.context.Instance;
import org.genthz.context.Node;
import org.genthz.context.Typeable;
import org.genthz.reflection.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

class FieldInstanceAccessor<T> extends AbstractAccessor<T> implements Node<String>, Typeable {
    private final Instance<T> upInstance;

    private final Field field;

    private final Type type;

    public FieldInstanceAccessor(Instance<T> upInstance, Field field, Type type) {
        this.upInstance = Objects.requireNonNull(upInstance);
        this.field = Objects.requireNonNull(field);
        this.type = type;
    }

    @Override
    public T get() {
        return Util.getFieldValue(this.field, this.upInstance.instance());
    }

    @Override
    public void set(T value) throws IllegalStateException {
        Util.setFieldValue(this.field, this.upInstance.instance(), value);
    }

    @Override
    public String node() {
        return this.field.getName();
    }

    @Override
    public Type type() {
        return this.type;
    }
}
