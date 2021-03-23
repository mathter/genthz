/*
 * Generated - testing becomes easier
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
package org.genthz.loly;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.configuration.dsl.DslFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

public class DefaultConfigurationTest {

    private static ObjectFactory OBJECT_FACTORY;

    @BeforeAll
    private static void init() {
        OBJECT_FACTORY = ObjectFactoryProducer
                .producer()
                .factory(
                        new DefaultConfiguration(
                                DslFactory.dsl()
                        )
                );
    }

    @Test
    public void testbyte() {
        Byte value = OBJECT_FACTORY.build(byte.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testByte() {
        Byte value = OBJECT_FACTORY.build(Byte.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testshort() {
        Short value = OBJECT_FACTORY.build(short.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testShort() {
        Short value = OBJECT_FACTORY.build(Short.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testInt() {
        Integer value = OBJECT_FACTORY.build(int.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testInteger() {
        Integer value = OBJECT_FACTORY.build(Integer.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testlong() {
        Long value = OBJECT_FACTORY.build(long.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testLong() {
        Long value = OBJECT_FACTORY.build(Long.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testfloat() {
        Float value = OBJECT_FACTORY.build(float.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testFloat() {
        Float value = OBJECT_FACTORY.build(Float.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testdouble() {
        Double value = OBJECT_FACTORY.build(double.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testDouble() {
        Double value = OBJECT_FACTORY.build(Double.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testString() {
        String value = OBJECT_FACTORY.build(String.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testUuid() {
        UUID value = OBJECT_FACTORY.build(UUID.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testDate() {
        Date value = OBJECT_FACTORY.build(Date.class);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testCollection() {
        Collection<?> value = OBJECT_FACTORY.build(Collection.class);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(ArrayList.class, value.getClass());

        value.stream().forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testCollectionList() {
        Collection<?> value = OBJECT_FACTORY.build(List.class);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(ArrayList.class, value.getClass());

        value.stream().forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testCollectionSet() {
        Collection<?> value = OBJECT_FACTORY.build(Set.class);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(HashSet.class, value.getClass());

        value.stream().forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testCollectionQueue() {
        Collection<?> value = OBJECT_FACTORY.build(Queue.class);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(ArrayDeque.class, value.getClass());

        value.stream().forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testCollectionDeque() {
        Collection<?> value = OBJECT_FACTORY.build(Deque.class);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(ArrayDeque.class, value.getClass());

        value.stream().forEach(e -> Assertions.assertNotNull(e));
    }
}
