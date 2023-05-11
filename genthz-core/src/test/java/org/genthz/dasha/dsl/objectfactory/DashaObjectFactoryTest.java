package org.genthz.dasha.dsl.objectfactory;

import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.dsl.Dsl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DashaObjectFactoryTest {

    @ParameterizedTest
    @MethodSource("data")
    public void test(Dsl dsl, Consumer assertion, Class clazz, Type... typeArguments) {
        final ObjectFactory objectFactory = new DashaObjectFactory(dsl.build());
        final Object instance = objectFactory.get(clazz, typeArguments);

        assertion.accept(instance);
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path("name")
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.path("name")
                                            .strict(String.class)
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                ),
                Arguments.of(
                        Optional.of(new DashaDsl().def())
                                .map(e -> {
                                    e.strict(SimpleTestModel.class)
                                            .path("name")
                                            .simple(ctx -> "Name");
                                    return e;
                                })
                                .get(),
                        (Consumer<SimpleTestModel>) e -> {
                            Assertions.assertNotNull(e);
                            Assertions.assertEquals("Name", e.getName());
                        },
                        SimpleTestModel.class,
                        null
                )
        );
    }
}
