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
package org.genthz.reflection.reference;

import org.apache.commons.lang3.ObjectUtils;
import org.genthz.FieldMatchers;
import org.genthz.function.FieldMatcher;
import org.genthz.reflection.Util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class ReferenceUtil {
    public static <T, R> Method method(GetMethodReference<T, R> reference) {
        for (Class<?> clazz = reference.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                final Method writeReplace = clazz.getDeclaredMethod("writeReplace");
                writeReplace.setAccessible(true);
                final Object replacement = Util.apply(writeReplace, method ->
                        {
                            try {
                                return method.invoke(reference);
                            } catch (ReflectiveOperationException e) {
                                throw new IllegalStateException(
                                        String.format(
                                                "Can't get java.lang.reflect.Method from reference %s!",
                                                reference
                                        ),
                                        e);
                            }
                        }
                );

                if (replacement instanceof SerializedLambda) {
                    final SerializedLambda serializedLambda = (SerializedLambda) replacement;
                    final String className = serializedLambda.getImplClass().replace('/', '.');
                    final ClassLoader classLoader = ObjectUtils.defaultIfNull(serializedLambda.getClass().getClassLoader(), GetMethodReference.class.getClassLoader());
                    final Class<?> targetClass = Class.forName(className, true, classLoader);
                    final Method method = targetClass.getMethod(serializedLambda.getImplMethodName());

                    return method;
                } else {
                    break;
                }
            } catch (NoSuchMethodException e) {
                continue;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(
                        String.format(
                                "Can't get java.lang.reflect.Method from reference %s!",
                                reference
                        ),
                        e);
            }
        }

        return null;
    }

    public static <T, R> Method method(SetMethodReference<T, R> reference) {
        for (Class<?> clazz = reference.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                final Method writeReplace = clazz.getDeclaredMethod("writeReplace");
                writeReplace.setAccessible(true);
                final Object replacement = Util.apply(writeReplace, method -> {
                            try {
                                return method.invoke(reference);
                            } catch (ReflectiveOperationException e) {
                                throw new IllegalStateException(
                                        String.format(
                                                "Can't get java.lang.reflect.Method from reference %s!",
                                                reference
                                        ),
                                        e);
                            }
                        }
                );

                if (replacement instanceof SerializedLambda) {
                    final SerializedLambda serializedLambda = (SerializedLambda) replacement;
                    final String className = serializedLambda.getImplClass().replace('/', '.');
                    final ClassLoader classLoader = ObjectUtils.defaultIfNull(serializedLambda.getClass().getClassLoader(), GetMethodReference.class.getClassLoader());
                    final Class<?> targetClass = Class.forName(className, true, classLoader);
                    final String methodName = serializedLambda.getImplMethodName();
                    final Method method = Stream.of(targetClass.getDeclaredMethods())
                            .filter(m -> methodName.equals(m.getName()) && m.getParameterCount() == 1)
                            .findAny()
                            .get();

                    return method;
                } else {
                    break;
                }
            } catch (NoSuchMethodException e) {
                continue;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(
                        String.format(
                                "Can't get java.lang.reflect.Method from reference %s!",
                                reference
                        ),
                        e);
            }
        }

        return null;
    }

    public static String propertyName(Method method) {
        final String result;
        final Class<?> declaring = method.getDeclaringClass();

        try {
            result = Optional.of(Introspector.getBeanInfo(declaring))
                    .map(e -> Stream.of(e.getPropertyDescriptors()))
                    .orElse(Stream.empty())
                    .filter(e -> method.equals(e.getReadMethod()) || method.equals(e.getWriteMethod()))
                    .map(PropertyDescriptor::getName)
                    .findFirst()
                    .get();
        } catch (Exception e) {
            throw new IllegalStateException(
                    String.format("Can't get property name from method %s!", method),
                    e);
        }

        return result;
    }
}
