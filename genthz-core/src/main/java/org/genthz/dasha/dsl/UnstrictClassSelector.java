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

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.function.Selector;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

class UnstrictClassSelector extends TypeSelector {
    public UnstrictClassSelector(Selector parent, Type type) {
        super(parent, type);
    }

    @Override
    public Stream<Parameter> params() {
        return Stream.concat(
                super.params(),
                Stream.of(Parameter.of("type_matching", "unstrict"))
        );
    }

    @Override
    public int effective() {
        return super.effective() + UnstrictClassSelector.effective(this.type);
    }

    private static int effective(Type type) {
        final int result;

        if (type instanceof Class) {
            result = 0;
        } else if (type instanceof ParameterizedType) {
            result = Stream.of(((ParameterizedType) type).getActualTypeArguments())
                    .map(e -> UnstrictClassSelector.effective(e))
                    .reduce(0, (l, r) -> l + r);
        } else if (type instanceof GenericArrayType) {
            result = 1;
        } else {
            throw new IllegalStateException(
                    type + " is a valud type! Must be java.lang.Class, java.lang.reflect.GenericArrayType, java.lang.reflect.ParameterizedType!"
            );
        }

        return result;
    }

    @Override
    public boolean test(Context context) {

        return context instanceof InstanceContext
                && TypeUtils.isAssignable(this.down(((InstanceContext<?>) context).type()), this.down(this.type))
                && super.test(context);
    }
}
