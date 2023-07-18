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
package org.genthz.etalon;

import org.genthz.ObjectFactory;
import org.genthz.etalon.model.Simple;
import org.genthz.etalon.model.SimpleGeneric;
import org.genthz.etalon.model.TestEnum;
import org.genthz.util.StreamUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Etalon tests for {@linkplain ObjectFactory}.
 */
public abstract class EtalonObjectFactoryTest extends AbstractEtalonObjectFactoryTest {
    @ParameterizedTest
    @DisplayName("Default instance builders and fillers test.")
    @MethodSource("data")
    public void testDefaults(Class<?> clazz, Type... typeArguments) {
        final Object instance = this.objectFactory().get(clazz, typeArguments);

        Assertions.assertNotNull(instance);
    }

    @Test
    @DisplayName("Simple class object generation test.")
    public void testSimple() {
        final Simple value = this.objectFactory().get(Simple.class);
        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getStringField());
        Assertions.assertNotNull(value.getDateField());
    }

    @Test
    @DisplayName("Array generation test.")
    public void testArrayClass() {
        final String[] value = this.objectFactory().get(String[].class);
        Assertions.assertNotNull(value);
        Assertions.assertEquals(this.objectFactory().generationProvider().defaults().defaultCollectionSize(), value.length);
        Stream.of(value).forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertEquals(String.class, e.getClass());
        });
    }

    @Test
    @DisplayName("Enum generation test")
    public void testEnum() {
        final TestEnum value = this.objectFactory().get(TestEnum.class);
        Assertions.assertNotNull(value);
    }

    @Test
    @DisplayName("Stream generation test.")
    public void testStream() {
        final Stream<String> value = this.objectFactory().get(Stream.class, String.class);
        Assertions.assertNotNull(value);

        long count = value
                .peek(e -> {
                    Assertions.assertTrue(e instanceof String);
                    Assertions.assertNotNull(e);
                })
                .map(e -> 1)
                .count();

        Assertions.assertEquals(this.objectFactory().generationProvider().defaults().defaultCollectionSize(), count);
    }

    @ParameterizedTest
    @DisplayName("Generic generation test.")
    @MethodSource("types")
    public void testSimpleGeneric(Class<?> keyClass, Class<?> valueClass) {
        final SimpleGeneric value = this.objectFactory().get(SimpleGeneric.class, keyClass, valueClass);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.gettField());
        Assertions.assertNotNull(value.getCollectionField());
        value.getCollectionField().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getListField());
        value.getListField().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getSetField());
        value.getSetField().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getMapField());
        value.getMapField().keySet().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(keyClass.equals(e.getClass()));
        });
        value.getMapField().values().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getArrayField());
    }

    @Test
    @DisplayName("Generic generation test without type arguments.")
    public void testSimpleGenericWithoutTypeArguments() {
        final SimpleGeneric value = this.objectFactory().get(SimpleGeneric.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.gettField());
        Assertions.assertNotNull(value.getCollectionField());
        value.getCollectionField().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(Object.class.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getListField());
        value.getListField().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(Object.class.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getSetField());
        value.getSetField().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(Object.class.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getMapField());
        value.getMapField().keySet().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(Object.class.equals(e.getClass()));
        });
        value.getMapField().values().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(Object.class.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.getArrayField());
    }

    @Test
    public void testRecursion() {
        final Recursion instance = this.objectFactory().get(Recursion.class);

        Assertions.assertNotNull(instance);
        Assertions.assertEquals(
                this.objectFactory().generationProvider().defaults().defaultMaxGenerationDepth().apply(null),
                StreamUtil.of(instance, Recursion::getNext).count()
        );
    }

    private static Stream<Arguments> types() {
        return Stream.of(
                Arguments.of(Boolean.class, Boolean.class),
                Arguments.of(String.class, String.class),
                Arguments.of(BigDecimal.class, BigDecimal.class),
                Arguments.of(Simple.class, Boolean.class),
                Arguments.of(Boolean.class, Simple.class),
                Arguments.of(String.class, Simple.class)
        );
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(Boolean.class, null),
                Arguments.of(boolean.class, null),
                Arguments.of(Short.class, null),
                Arguments.of(short.class, null),
                Arguments.of(Integer.class, null),
                Arguments.of(int.class, null),
                Arguments.of(Long.class, null),
                Arguments.of(long.class, null),
                Arguments.of(Float.class, null),
                Arguments.of(float.class, null),
                Arguments.of(Double.class, null),
                Arguments.of(double.class, null),
                Arguments.of(String.class, null),
                Arguments.of(BigInteger.class, null),
                Arguments.of(BigDecimal.class, null),
                Arguments.of(Date.class, null),
                Arguments.of(LocalDate.class, null),
                Arguments.of(LocalTime.class, null),
                Arguments.of(LocalDateTime.class, null),
                Arguments.of(OffsetTime.class, null),
                Arguments.of(OffsetDateTime.class, null),
                Arguments.of(ZonedDateTime.class, null),
                Arguments.of(ZoneId.class, null),
                Arguments.of(Collection.class, new Class[]{String.class}),
                Arguments.of(List.class, new Class[]{String.class}),
                Arguments.of(Queue.class, new Class[]{String.class}),
                Arguments.of(Deque.class, new Class[]{String.class}),
                Arguments.of(Set.class, new Class[]{String.class}),
                Arguments.of(Map.class, new Class[]{String.class, String.class})
        );
    }

    public static class Recursion {
        private Recursion next;

        public Recursion getNext() {
            return next;
        }

        public void setNext(Recursion next) {
            this.next = next;
        }
    }
}
