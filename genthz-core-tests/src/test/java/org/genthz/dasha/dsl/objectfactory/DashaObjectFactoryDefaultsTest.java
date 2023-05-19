package org.genthz.dasha.dsl.objectfactory;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
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

public class DashaObjectFactoryDefaultsTest {
    private final ObjectFactory objectFactory = new DashaObjectFactory();

    @ParameterizedTest
    @MethodSource("data")
    public void testDefaults(Class<?> clazz, Type... typeArguments) {
        final Object instance = this.objectFactory.get(clazz, typeArguments);

        Assertions.assertNotNull(instance);
    }

    @Test
    public void testSimpleTestModel() {
        final SimpleTestModel instance = this.objectFactory.get(SimpleTestModel.class);

        Assertions.assertNotNull(instance);
        Assertions.assertNotNull(instance.getName());
        Assertions.assertNotNull(instance.getLastName());
        Assertions.assertNotNull(instance.getBirthDay());
        Assertions.assertNotNull(instance.getAge());
    }

    @Test
    public void testSimpleGenericModel() {
        final SimpleGenericModel instance = this.objectFactory.get(SimpleGenericModel.class, LocalDateTime.class, String.class);

        Assertions.assertNotNull(instance);
        Assertions.assertNotNull(instance.getName());
        Assertions.assertNotNull(instance.getLastName());
        Assertions.assertNotNull(instance.getBirthDay());
        Assertions.assertNotNull(instance.getAge());

        Assertions.assertNotNull(instance.getOtherNames());
        Assertions.assertEquals(5, instance.getOtherNames().size());

        instance.getOtherNames().stream()
                .forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testSimpleGenericModelWithoutTypeArguments() {
        final SimpleGenericModel instance = this.objectFactory.get(SimpleGenericModel.class);

        Assertions.assertNotNull(instance);
        Assertions.assertNotNull(instance.getName());
        Assertions.assertNotNull(instance.getLastName());
        Assertions.assertNotNull(instance.getBirthDay());
        Assertions.assertNotNull(instance.getAge());

        Assertions.assertNotNull(instance.getOtherNames());
        Assertions.assertEquals(5, instance.getOtherNames().size());

        instance.getOtherNames().stream()
                .forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testSimpleGenericModelWithParameterizedTest() {
        final SimpleGenericModel instance = this.objectFactory.get(
                SimpleGenericModel.class,
                TypeUtils.parameterize(Collection.class, LocalDateTime.class),
                TypeUtils.parameterize(Set.class, String.class)
        );

        Assertions.assertNotNull(instance);
        Assertions.assertNotNull(instance.getName());
        Assertions.assertNotNull(instance.getLastName());
        Assertions.assertNotNull(instance.getBirthDay());
        Assertions.assertNotNull(instance.getAge());

        Assertions.assertNotNull(instance.getOtherNames());
        Assertions.assertEquals(5, instance.getOtherNames().size());

        instance.getOtherNames().stream()
                .forEach(e -> Assertions.assertNotNull(e));
    }

    @Test
    public void testArray0() {
        final String[][] instance = this.objectFactory.get(String[][].class);

        Assertions.assertNotNull(instance);
        Assertions.assertEquals(
                this.objectFactory.generationProvider().defaults().defaultCollectionSize(),
                instance.length
        );
    }

    @Test
    public void testArray1() {
        final SimpleGenericModel instance = this.objectFactory.get(SimpleGenericModel.class, String[].class, String.class);

        Assertions.assertNotNull(instance);
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
}
