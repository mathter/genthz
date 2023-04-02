package org.genthz.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class GenericUtil {
    private GenericUtil() {
    }

    public static Type[] typeParameters(Type type) {
        final Type[] result;

        if (type instanceof Class) {
            result = ((Class<?>) type).getTypeParameters();
        } else if (type instanceof ParameterizedType) {
            result = ((ParameterizedType) type).getActualTypeArguments();
        } else {
            throw new IllegalArgumentException(
                    String.format("Invalid type: %s . Must be java.lang.Class or java.lang.reflect.ParameterizedType", type)
            );
        }

        return result;
    }

    public static Map<TypeVariable, Type> attribution(GenericDeclaration genericDeclaration, Type... genericTypes) {
        final Map<TypeVariable, Type> map = new HashMap<>();
        final TypeVariable[] declared = genericDeclaration.getTypeParameters();

        if (declared.length == genericTypes.length) {
            for (int i = 0; i < declared.length; i++) {
                map.put(declared[i], genericTypes[i]);
            }
        } else {
            throw new IllegalArgumentException(
                    String.format("actual type parameter count %d not equals to the generic argument type count %d!", declared.length, genericTypes.length)
            );
        }

        return map;
    }

    public static <T> Class<T> rawType(Type type) {
        return GenericUtil.rawType(type, Collections.emptyMap());
    }

    public static <T> Class<T> rawType(Type type, Map<TypeVariable, Type> map) {
        final Class<T> result;

        if (type instanceof Class) {
            result = (Class<T>) type;
        } else if (type instanceof TypeVariable) {
            result = GenericUtil.rawType(map.get(type), map);
        } else if (type instanceof ParameterizedType) {
            result = GenericUtil.rawType(((ParameterizedType) type).getRawType(), map);
        } else if (type instanceof GenericArrayType) {
            result = (Class<T>) Array.newInstance(GenericUtil.rawType(((GenericArrayType) type).getGenericComponentType(), map), 0).getClass();
        } else {
            result = (Class<T>) Object.class;
        }

        return result;
    }

    public static Type resolve(Type type, Map<TypeVariable, Type> map) {
        final Type result;

        if (type instanceof Class) {
            if (((Class<?>) type).getTypeParameters().length == 0) {
                result = type;
            } else {
                result = ParameterizedTypeImpl.make(
                        (Class<?>) type,
                        Stream.of(((Class<?>) type).getTypeParameters())
                                .map(e -> GenericUtil.resolve(e, map))
                                .toArray(i -> new Type[i]),
                        null
                );
            }
        } else if (type instanceof TypeVariable) {
            result = GenericUtil.resolve(map.get(type), map);
        } else if (type instanceof ParameterizedType) {
            final ParameterizedType origin = (ParameterizedType) type;

            result = ParameterizedTypeImpl.make(
                    (Class<?>) origin.getRawType(),
                    Stream.of(origin.getActualTypeArguments())
                            .map(e -> GenericUtil.resolve(e, map))
                            .toArray(i -> new Type[i]),
                    origin.getOwnerType()
            );

        } else if (type instanceof GenericArrayType) {
            result = GenericArrayTypeImpl.make(
                    GenericUtil.resolve(((GenericArrayType) type).getGenericComponentType(), map)
            );
        } else {
            throw new IllegalStateException(
                    String.format("Can't found Class for type: %s", type)
            );
        }

        return result;
    }
}
