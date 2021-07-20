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

import org.genthz.function.CaclulatedConstructorInstanceBuilder;
import org.genthz.function.ConstructorInstanceBuilder;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Constructor;
import java.util.function.Predicate;

/**
 * Collection of the {@linkplain Selectable} for construct {@linkplain InstanceBuilder}.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public final class InstanceBuilders {

    public static <T> InstanceBuilder<T> def() {
        return InstanceBuilders.byConstructorArgCount(0);
    }

    public static <T> InstanceBuilder<T> byConstructor(final Constructor<T> constructor) {
        return new ConstructorInstanceBuilder<>(constructor);
    }

    public static <T> InstanceBuilder<T> byConstructorArgCount(final int constructorArgumentCount) {
        return new CaclulatedConstructorInstanceBuilder<>(c -> c.getParameterCount() == constructorArgumentCount);
    }

    public static <T> InstanceBuilder<T> byArgumentTypes(final Class... constructorArgumentTypes) {
        return new CaclulatedConstructorInstanceBuilder<>(c -> {
            try {
                return c.getDeclaringClass().getConstructor(constructorArgumentTypes).equals(c);
            } catch (NoSuchMethodException e) {
                return false;
            }
        });
    }

    public static <T> Predicate<Constructor<T>> byDefaultConstructor() {
        return c -> c.getParameterCount() == 0;
    }

    private InstanceBuilders() {
    }
}
