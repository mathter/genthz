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
