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
package org.genthz.function;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.genthz.context.Context;
import org.genthz.util.Constants;

import java.lang.reflect.Constructor;

public class ConstructorInstanceBuilder<T> extends AbstractConstructorInstanceBuilder<T> {
    private final Constructor<T> constructor;

    public ConstructorInstanceBuilder(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    @Override
    protected Constructor<T> getConstructor(Context<T> context) {
        return this.constructor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
    }
}
