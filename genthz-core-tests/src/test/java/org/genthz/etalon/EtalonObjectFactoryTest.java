package org.genthz.etalon;

import org.genthz.ObjectFactory;
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
public abstract class EtalonObjectFactoryTest {
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
        Assertions.assertNotNull(value.stringField);
        Assertions.assertNotNull(value.dateField);
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

    @ParameterizedTest
    @DisplayName("Generic generation test.")
    @MethodSource("types")
    public void testSimpleGeneric(Class<?> keyClass, Class<?> valueClass) {
        final SimpleGeneric value = this.objectFactory().get(SimpleGeneric.class, keyClass, valueClass);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.tField);
        Assertions.assertNotNull(value.collectionField);
        value.collectionField.stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.listField);
        value.listField.stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.setField);
        value.setField.stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.mapField);
        value.mapField.keySet().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(keyClass.equals(e.getClass()));
        });
        value.mapField.values().stream().forEach(e -> {
            Assertions.assertNotNull(e);
            Assertions.assertTrue(valueClass.equals(e.getClass()));
        });
        Assertions.assertNotNull(value.arrayField);
    }

    public abstract ObjectFactory objectFactory();

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

    public static class Simple {
        private String stringField;

        private Date dateField;
    }

    public static class SimpleGeneric<K, T> {
        private T tField;

        private Collection<T> collectionField;

        private List<T> listField;

        private Set<T> setField;

        private Map<K, T> mapField;

        private T[] arrayField;
    }
}
