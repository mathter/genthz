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
package org.genthz.configuration.dsl;

import java.lang.reflect.Constructor;
import java.util.function.Predicate;

/**
 * Collection of the {@linkplain Selectable} for construct {@linkplain org.genthz.InstanceBuilder}.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface InstanceBuilders {

    public <T> Selectable byConstructor(Predicate<Constructor<T>> predicate);

    public static <T> Predicate<Constructor<T>> byConstructor(final Constructor<T> constructor) {
        return c -> constructor.equals(c);
    }

    public static <T> Predicate<Constructor<T>> byArgumentCount(final int constructorArgumentCount) {
        return c -> c.getParameterCount() == constructorArgumentCount;
    }

    public static <T> Predicate<Constructor<T>> byArgumentTypes(final Class[] constructorArgumentTypes) {
        return c -> {
            try {
                return c.getDeclaringClass().getConstructor(constructorArgumentTypes).equals(c);
            } catch (NoSuchMethodException e) {
                return false;
            }
        };
    }

    public static <T> Predicate<Constructor<T>> byDefaultConstructor() {
        return c -> c.getParameterCount() == 0;
    }
}
