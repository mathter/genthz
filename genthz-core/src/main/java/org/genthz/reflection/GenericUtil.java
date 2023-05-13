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
package org.genthz.reflection;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenericUtil {
    private final boolean strict;

    public GenericUtil(boolean strict) {
        this.strict = strict;
    }

    public Class getRawClass(Map<TypeVariable<?>, Type> variableTypeMap, Type type) {
        final Type unrolled = TypeUtils.unrollVariables(variableTypeMap, type);

        if (!(unrolled instanceof Class)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Can't get raw type of %s. Unroll result is %s",
                            type,
                            unrolled
                    )
            );
        }

        return (Class) unrolled;
    }

    public Map<TypeVariable<?>, Type> getActualTypeArguments(Type type) {
        final Map<TypeVariable<?>, Type> result;

        if (type instanceof Class) {
            final Type[] typeParameters = ((Class) type).getTypeParameters();

            if (this.strict && typeParameters.length > 0) {
                throw new IllegalArgumentException(
                        String.format(
                                "%s has unsigned parameters %s",
                                type,
                                Stream.of(typeParameters).map(Object::toString).collect(Collectors.joining())
                        )
                );
            } else {
                result = Stream.of(typeParameters)
                        .collect(Collectors.toMap(e -> (TypeVariable) e, e -> Object.class));
            }
        } else if (type instanceof ParameterizedType) {
            result = TypeUtils.getTypeArguments((ParameterizedType) type);
        } else if (type instanceof GenericArrayType) {
            result = Collections.emptyMap();
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "%s is not valid parameters",
                            type
                    )
            );
        }

        return result;
    }

    public Type parameterize(Class clazz, Type... typeArguments) throws IllegalArgumentException {
        final Type result;
        final TypeVariable<?>[] typeParameters = clazz.getTypeParameters();

        if (typeParameters.length == 0) {
            if (typeArguments == null || typeArguments.length == 0) {
                result = clazz;
            } else {
                throw new IllegalArgumentException(
                        String.format(
                                "Invalid count of parameters specified for class %s: expected %d, got %s ",
                                clazz,
                                typeParameters.length,
                                typeArguments != null ? typeArguments.length : null
                        )
                );
            }
        } else {
            if (typeArguments == null || typeArguments.length == 0) {
                if (this.strict) {
                    throw new IllegalArgumentException(
                            String.format(
                                    "Invalid count of parameters specified for class %s: expected %d, got %s ",
                                    clazz,
                                    typeParameters.length,
                                    typeArguments != null ? typeArguments.length : null
                            )
                    );
                } else {
                    typeArguments = IntStream.range(0, typeParameters.length)
                            .mapToObj(i -> Object.class)
                            .toArray(i -> new Type[i]);
                }
            }

            result = TypeUtils.parameterize(clazz, typeArguments);
        }

        return result;
    }
}
