package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.DefaultCollectionFiller;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.UnitFiller;
import org.junit.jupiter.api.Assertions;
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
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

public class DashaDslTest {
    private final ContextFactory contextFactory = new DashaContextFactory();

    private final DashaDsl dsl = new DashaDsl().def();

    final GenerationProvider generationProvider = this.dsl.build();

    @ParameterizedTest
    @MethodSource("data")
    public void testDefauts(Class<?> clazz, Type... typeArguments) {
        final InstanceContext context = this.contextFactory.single(clazz, typeArguments);
        final InstanceBuilderConsumer ib = generationProvider.instanceBuilder(context);
        final Filler filler = generationProvider.filler(context);

        Assertions.assertNotNull(ib);
        Assertions.assertNotNull(filler);

        if (Collection.class.isAssignableFrom(clazz)) {
            Assertions.assertTrue(filler instanceof DefaultCollectionFiller);
        } else {
            Assertions.assertTrue(filler instanceof UnitFiller);
        }

        ib.instance(context);
        Assertions.assertNotNull(context.instance());
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
                Arguments.of(Set.class, new Class[]{String.class})
        );
    }
}
