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

    public ParameterizedType parameterize(Class clazz, Type... typeArguments) throws IllegalArgumentException {
        final ParameterizedType result;
        final TypeVariable<?>[] typeParameters = clazz.getTypeParameters();

        if ((typeArguments == null || typeArguments.length == 0) && typeParameters.length != 0) {
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

        return result;
    }
}
