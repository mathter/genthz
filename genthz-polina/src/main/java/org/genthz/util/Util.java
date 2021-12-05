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
package org.genthz.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Utilit class for Generated framework.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 3.0.0
 * @since 3.0.0
 */
public final class Util {

    private static final Map<Class<?>, Class<?>> BOXED_PRIMITIVE;

    private static final Map<Class<?>, Class<?>> PRIMITIVE_BOXED;

    private static final Collection<Class<?>> SIMPLE_CLASSES;

    static {
        final Map<Class<?>, Class<?>> boxed_primitive = new HashMap<>();
        boxed_primitive.put(Boolean.class, boolean.class);
        boxed_primitive.put(Byte.class, byte.class);
        boxed_primitive.put(Short.class, short.class);
        boxed_primitive.put(Integer.class, int.class);
        boxed_primitive.put(Long.class, long.class);
        boxed_primitive.put(Float.class, float.class);
        boxed_primitive.put(Double.class, double.class);

        BOXED_PRIMITIVE = Collections.unmodifiableMap(boxed_primitive);

        final Map<Class<?>, Class<?>> primitive_boxed = new HashMap<>();
        for (Map.Entry<Class<?>, Class<?>> entry : boxed_primitive.entrySet()) {
            primitive_boxed.put(entry.getValue(), entry.getKey());
        }

        PRIMITIVE_BOXED = Collections.unmodifiableMap(primitive_boxed);

        final Collection<Class<?>> simple = new HashSet<>();
        simple.add(String.class);
        simple.add(UUID.class);
        simple.add(Date.class);
        simple.add(BigDecimal.class);
        simple.add(BigInteger.class);
        simple.addAll(BOXED_PRIMITIVE.keySet());
        simple.addAll(PRIMITIVE_BOXED.keySet());

        SIMPLE_CLASSES = Collections.unmodifiableCollection(simple);
    }

    /**
     * Method return true i argument is a primitive or boxing type.
     *
     * @param clazz
     * @return
     */
    public static final boolean isSimple(Class<?> clazz) {
        return SIMPLE_CLASSES.contains(clazz) || Enum.class.isAssignableFrom(clazz);
    }

    /**
     * Method return true if argument is a primitive type.
     *
     * @param clazz class
     * @return true if clazz is a primitive and vice versa.
     */
    public static final boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive();
    }

    /**
     * Method return true if argument is a boxing type.
     *
     * @param clazz class
     * @return true if argument is is a boxing type.
     */
    public static final boolean isBoxing(Class<?> clazz) {
        return BOXED_PRIMITIVE.containsKey(clazz) || PRIMITIVE_BOXED.containsKey(clazz);
    }

    public static final <A, B> Class<B> boxed(Class<A> clazz) {
        return (Class<B>) BOXED_PRIMITIVE.getOrDefault(clazz, PRIMITIVE_BOXED.get(clazz));
    }

    /**
     * Method return Boxing class for primitive as method argument and vice versa.
     *
     * @param clazz class
     * @return dual class
     */
    public static Class<?> getDual(Class<?> clazz) {
        Class<?> result;

        if ((result = BOXED_PRIMITIVE.get(clazz)) == null) {
            if ((result = PRIMITIVE_BOXED.get(clazz)) == null) {
                result = clazz;
            }
        }

        return result;
    }

    public static <T extends AccessibleObject, R> R apply(T accessibleObject, Function<T, R> f) {

        boolean originAccessibleState = accessibleObject.isAccessible();
        accessibleObject.setAccessible(true);

        try {
            return f.apply(accessibleObject);
        } finally {
            accessibleObject.setAccessible(originAccessibleState);
        }
    }

    public static <T> T invoke(Object target, Method method, Object... args) {
        return Util.apply(method, (Function<Method, T>) m -> {
            try {
                return (T) m.invoke(target, args);
            } catch (Exception e) {
                throw new IllegalStateException("Can't invoke " + m, e);
            }
        });
    }

    public static <T> void setFieldValue(Field field, Object object, T value) {
        Util.apply(field, (Function<Field, Void>) f -> {
            try {
                f.set(object, value);
                return null;
            } catch (Exception e) {
                throw new IllegalStateException("Can't set value=" + value + " of " + f, e);
            }
        });
    }

    public static <T> T getFieldValue(Field field, Object object) {
        final T result = Util.apply(field, f -> {
            try {
                return (T) f.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Can't get value of field " + f + " of object " + object + " !", e);
            }
        });

        return result;
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... parameters) {
        final boolean originAccessibleState;
        try {
            originAccessibleState = constructor.isAccessible();

            try {
                constructor.setAccessible(true);
                return constructor.newInstance(parameters);
            } finally {
                constructor.setAccessible(originAccessibleState);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Can't create object of class '" + constructor.getDeclaringClass() + "'!", e);
        }
    }

    public static Class<?> getSupplierReturnType(ParameterizedType type) {
        final Class<?> result;

        if (type != null) {
            if (type.getRawType().equals(Supplier.class)) {
                result = (Class<?>) type.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("type parameter represents " + type.getRawType() + ". Must be " + Supplier.class + "!");
            }
        } else {
            result = null;
        }

        return result;
    }

    public static Class<?> getFunctionArgumentType(ParameterizedType type) {
        final Class<?> result;

        if (type != null) {
            if (type.getRawType().equals(Function.class)) {
                result = (Class<?>) type.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("type parameter represents " + type.getRawType() + ". Must be " + Function.class + "!");
            }
        } else {
            result = null;
        }

        return result;
    }

    public static Class<?> getFunctionArgumentType(Function<?, ?> function) {
        return Util.getFunctionArgumentType(function.getClass());
    }

    public static Class<?> getFunctionArgumentType(Class<? extends Function> clazz) {
        final Class<?> result;

        if (clazz != null) {
            result = Stream
                    .of(clazz.getMethods())
                    .filter(m -> "apply".equals(m.getName()) && m.getParameterCount() == 1)
                    .map(m -> m.getParameterTypes()[0])
                    .findFirst()
                    .get();
        } else {
            result = null;
        }

        return result;
    }

    public static <T> Constructor<T>[] getConstructors(Class<T> clazz) {
        return (Constructor<T>[]) clazz.getDeclaredConstructors();
    }
}
