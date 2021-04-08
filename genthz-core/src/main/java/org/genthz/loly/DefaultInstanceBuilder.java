/*
 * Generated - testing becomes easier
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
package org.genthz.loly;

import org.genthz.Util;

import java.lang.reflect.Constructor;
import java.util.function.Predicate;

class DefaultInstanceBuilder<T> extends CalculatedConstructorBasedInstanceBuilder<T> {

    public DefaultInstanceBuilder(final Class<T> clazz) {
        super(DefaultInstanceBuilder.buildPredicate(clazz), null);
    }

    private static <T> Predicate<Constructor<T>> buildPredicate(Class<T> clazz) {
        final Constructor<T>[] constructors = Util.getConstructors(clazz);
        return constructor -> constructors.length == 1 || constructor.getParameterCount() == 0;
    }
}