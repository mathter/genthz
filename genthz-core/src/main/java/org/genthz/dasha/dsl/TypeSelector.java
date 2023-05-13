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
package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.function.Selector;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class TypeSelector extends AbstractSelector {
    protected final Type type;

    public TypeSelector(Selector parent, Type type) {
        super(parent);
        this.type = type;
    }

    @Override
    public boolean test(Context context) {
        return this.up().map(e -> e.test(context)).orElse(true);
    }

    protected Type down(Type type) {
        final Type result;

        if (type instanceof Class) {
            result = type;
        } else if (type instanceof ParameterizedType && ((ParameterizedType) type).getActualTypeArguments().length == 0) {
            result = ((ParameterizedType) type).getRawType();
        } else {
            result = type;
        }

        return result;
    }
}
