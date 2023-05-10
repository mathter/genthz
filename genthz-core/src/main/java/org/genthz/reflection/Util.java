package org.genthz.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.function.Function;

public final class Util {
    private Util() {
    }

    public static <T> void setFieldValue(Field field, Object object, T value) {
        Util.apply(field, (Function<Field, Void>) f -> {
            try {
                f.set(object, value);
                return null;
            } catch (Exception e) {
                throw new RuntimeException("Can't set value of " + f, e);
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

    public static <T extends AccessibleObject, R> R apply(T accessibleObject, Function<T, R> f) {
        final boolean originAccessibleState = accessibleObject.isAccessible();
        accessibleObject.setAccessible(true);

        try {
            return f.apply(accessibleObject);
        } finally {
            accessibleObject.setAccessible(originAccessibleState);
        }
    }
}
