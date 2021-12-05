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
package org.genthz.configuration.dsl;


import org.genthz.configuration.InstanceBuilder;
import org.genthz.configuration.dsl.function.ConstructorInstanceBuilder;
import org.genthz.context.context.Context;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public abstract class InstanceBuilders {
    private static final Predicate<Constructor> MIN_ARG_CONSTRUCTOR_PREDICATE = c -> {
        int min = Integer.MAX_VALUE;
        final Constructor<?> tmp;
        final Constructor<?>[] constructors = c.getDeclaringClass().getDeclaredConstructors();

        for (int i = 0; i < constructors.length; i++) {
            if (constructors[i].getParameterCount() < min) {
                min = constructors[i].getParameterCount();
            }
        }

        return c.getParameterCount() == min;
    };

    public static <T> InstanceBuilder<T> def() {
        return new ConstructorInstanceBuilder<T>() {
            @Override
            public Constructor<T> constructor(Context<T> context) {
                return ConstructorInstanceBuilder.constructor(c -> c.getParameterCount() == 0, context);
            }
        };
    }

    public static <T> InstanceBuilder<T> minArgCount() {
        return new ConstructorInstanceBuilder<T>() {
            @Override
            public Constructor<T> constructor(Context<T> context) {
                return ConstructorInstanceBuilder.constructor(MIN_ARG_CONSTRUCTOR_PREDICATE, context);
            }
        };
    }

    public static <T> InstanceBuilder<T> argsTypes(Class<?>... classes) {
        return new ConstructorInstanceBuilder<T>() {
            @Override
            public Constructor<T> constructor(Context<T> context) {
                return ConstructorInstanceBuilder.constructor(
                        c -> {
                            final boolean result;
                            final Class<?>[] argClasses = c.getParameterTypes();
                            if (classes == null || classes.length == 0) {
                                result = c.getParameterTypes().length == 0;
                            } else {
                                result = Arrays.equals(argClasses, classes);
                            }

                            return result;
                        },
                        context
                );
            }
        };
    }

    public static <T> InstanceBuilder<T> argCount(int count) {
        return new ConstructorInstanceBuilder<T>() {
            @Override
            public Constructor<T> constructor(Context<T> context) {
                return ConstructorInstanceBuilder.constructor(
                        c -> count == c.getParameterCount(),
                        context
                );
            }
        };
    }

    public static <T> InstanceBuilder<T> constructor(Constructor<T> constructor) {
        return new ConstructorInstanceBuilder<T>() {
            @Override
            public Constructor constructor(Context context) {
                return constructor;
            }
        };
    }

//    public static <T extends Collection> InstanceBuilder<T> collectionInstanceBuilder() {
//
//    }

    public InstanceBuilders() {
    }
}
