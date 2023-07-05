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
package org.genthz.function;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

@FunctionalInterface
public interface FieldMatcher {
    public boolean matche(Context context);

    public default boolean nonMatche(Context context) {
        return !this.matche(context);
    }



    public static Context of(Field field) {
        return new Context(field, null);
    }

    public static Context of(Field field, Map<TypeVariable<?>, Type> variableTypeMap) {
        return new Context(field, variableTypeMap);
    }

    public class Context {
        private final Field field;

        private final Map<TypeVariable<?>, Type> variableTypeMap;

        Context(Field field, Map<TypeVariable<?>, Type> variableTypeMap) {
            this.field = field;
            this.variableTypeMap = variableTypeMap;
        }

        public Type declaring() {
            return this.field.getDeclaringClass();
        }

        public Type type() {
            return TypeUtils.unrollVariables(this.variableTypeMap, this.field.getGenericType());
        }

        public String name() {
            return this.field.getName();
        }
    }
}
