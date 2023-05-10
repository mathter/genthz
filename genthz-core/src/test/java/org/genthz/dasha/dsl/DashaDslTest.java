package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Stage;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.InstanceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;

public class DashaDslTest {
    private final ContextFactory contextFactory = new DashaContextFactory();

    private final DashaDsl dsl = new DashaDsl().def();

    final GenerationProvider generationProvider = this.dsl.build();

    @ParameterizedTest
    @MethodSource("data")
    public void testDefauts(Class<?> clazz) {
        final InstanceContext context = this.contextFactory.single(clazz);
        final InstanceBuilder ib = generationProvider.instanceBuilder(context);

        Assertions.assertNotNull(ib);
        ib.instance(context);

        Assertions.assertNotNull(context.instance());
    }

    private static Stream<Arguments> data() {
        return Stream.of(
//                Arguments.of(Boolean.class),
//                Arguments.of(boolean.class),
//                Arguments.of(Short.class),
//                Arguments.of(short.class),
//                Arguments.of(Integer.class),
//                Arguments.of(int.class),
//                Arguments.of(Long.class),
//                Arguments.of(long.class),
//                Arguments.of(Float.class),
//                Arguments.of(float.class),
//                Arguments.of(Double.class),
//                Arguments.of(double.class),
//                Arguments.of(String.class),
//                Arguments.of(Date.class),
//                Arguments.of(LocalDate.class),
//                Arguments.of(LocalTime.class),
//                Arguments.of(LocalDateTime.class),
//                Arguments.of(OffsetTime.class),
//                Arguments.of(OffsetDateTime.class),
//                Arguments.of(ZonedDateTime.class),
//                Arguments.of(ZoneId.class),
                Arguments.of(Collection.class)
        );
    }
}
