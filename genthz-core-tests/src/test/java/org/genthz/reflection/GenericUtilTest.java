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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GenericUtilTest {
    @Test
    public void testStrictGetActualTypeArgumentsOfClass0() {
        final GenericUtil util = new GenericUtil(true);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(String.class);

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(0, variableTypeMap.size());
    }

    @Test
    public void testUnstrictGetActualTypeArgumentsOfClass0() {
        final GenericUtil util = new GenericUtil(false);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(String.class);

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(0, variableTypeMap.size());
    }

    @Test
    public void testStrictGetActualTypeArgumentsOfClass1() {
        final GenericUtil util = new GenericUtil(true);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(
                TypeUtils.parameterize(Collection.class, String.class)
        );

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(1, variableTypeMap.size());
        Assertions.assertEquals(
                Collection.class.getTypeParameters()[0],
                variableTypeMap.keySet().stream().findFirst().get()
        );
        Assertions.assertEquals(
                String.class,
                variableTypeMap.values().stream().findFirst().get()
        );
    }

    @Test
    public void testUntrictGetActualTypeArgumentsOfClass1() {
        final GenericUtil util = new GenericUtil(false);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(
                TypeUtils.parameterize(Collection.class, String.class)
        );

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(1, variableTypeMap.size());
        Assertions.assertEquals(
                Collection.class.getTypeParameters()[0],
                variableTypeMap.keySet().stream().findFirst().get()
        );
        Assertions.assertEquals(
                String.class,
                variableTypeMap.values().stream().findFirst().get()
        );
    }

    @Test
    public void testUntrictGetActualTypeArgumentsOfClass10() {
        final GenericUtil util = new GenericUtil(false);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(
                Collection.class
        );

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(1, variableTypeMap.size());
        Assertions.assertEquals(
                Collection.class.getTypeParameters()[0],
                variableTypeMap.keySet().stream().findFirst().get()
        );
        Assertions.assertEquals(
                Object.class,
                variableTypeMap.values().stream().findFirst().get()
        );
    }

    @Test
    public void testStrictGetActualTypeArgumentsOfClass2() {
        final GenericUtil util = new GenericUtil(true);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(
                String[].class
        );

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(0, variableTypeMap.size());
    }

    @Test
    public void testStrictGetActualTypeArgumentsOfClass3() {
        final GenericUtil util = new GenericUtil(true);
        final Map<TypeVariable<?>, Type> variableTypeMap = util.getActualTypeArguments(
                TypeUtils.genericArrayType(String.class)
        );

        Assertions.assertNotNull(variableTypeMap);
        Assertions.assertEquals(0, variableTypeMap.size());
    }

    @Test
    public void testStrictGetActualTypeArgumentsOfClassFail() {
        final GenericUtil util = new GenericUtil(true);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> util.getActualTypeArguments(Collection.class)
        );
    }

    @Test
    public void testStrictParameterize() {
        final GenericUtil util = new GenericUtil(true);
        final ParameterizedType type = (ParameterizedType) util.parameterize(List.class, String.class);

        Assertions.assertNotNull(type);
        Assertions.assertEquals(List.class, type.getRawType());
        Assertions.assertEquals(1, type.getActualTypeArguments().length);
        Assertions.assertEquals(String.class, type.getActualTypeArguments()[0]);
    }

    @Test
    public void testStrictParameterizeFail() {
        final GenericUtil util = new GenericUtil(true);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> util.parameterize(List.class)
        );
    }

    @Test
    public void testUnstrictParameterize() {
        final GenericUtil util = new GenericUtil(false);
        final Type type = util.parameterize(List.class, String.class);

        Assertions.assertNotNull(type);
        Assertions.assertTrue(type instanceof ParameterizedType);
        Assertions.assertEquals(List.class, ((ParameterizedType) type).getRawType());
        Assertions.assertEquals(1, ((ParameterizedType) type).getActualTypeArguments().length);
        Assertions.assertEquals(String.class, ((ParameterizedType) type).getActualTypeArguments()[0]);
    }

    @Test
    public void testUnstrictParameterizeWithoutTypeArguments() {
        final GenericUtil util = new GenericUtil(false);
        final Type type = util.parameterize(List.class);

        Assertions.assertNotNull(type);
        Assertions.assertTrue(type instanceof ParameterizedType);
        Assertions.assertEquals(List.class, ((ParameterizedType) type).getRawType());
        Assertions.assertEquals(1, ((ParameterizedType) type).getActualTypeArguments().length);
        Assertions.assertEquals(Object.class, ((ParameterizedType) type).getActualTypeArguments()[0]);
    }
}