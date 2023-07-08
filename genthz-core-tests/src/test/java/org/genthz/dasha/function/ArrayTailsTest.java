package org.genthz.dasha.function;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.genthz.Defaults;
import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaDefaults;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.dsl.Dsl;
import org.genthz.etalon.model.OneField;
import org.genthz.etalon.model.Simple;
import org.genthz.function.Filler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayTailsTest implements ArgumentsProvider {
    @ParameterizedTest
    @DisplayName("Check ArrayTails")
    @ArgumentsSource(ArrayTailsTest.class)
    public void test(Dsl dsl, Class<?> clazz, Type[] genericTypes, Consumer<Object> checker) {
        final ObjectFactory objectFactory = new DashaObjectFactory(dsl.build());
        final Object instance = objectFactory.get(clazz, genericTypes);

        checker.accept(instance);
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.<Supplier<Arguments>>of(
                        // 1
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(String[].class)
                                                    .tail(
                                                            ArrayTails.size(size)
                                                    );
                                        }
                                    }.def(),
                                    String[].class,
                                    null,
                                    baseChecker(size)
                            );
                        },

                        // 2
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>size(size)
                                                                    .componentSimple(ctx -> new OneField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 3
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>size(size)
                                                                    .<OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        //4
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>size(size)
                                                                    .<OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values)));
                        },

                        // 5
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>size(size)
                                                                    .<Simple>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 6
                        () -> {
                            final Defaults defaults = new DashaDefaults();
                            final int size = defaults.defaultCollectionSize();
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .componentSimple(ctx -> new OneField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 7
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentSimple(ctx -> new OneField(iterator.next().getField()))
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 8
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .size(size)
                                                                    .componentFiller((Filler<OneField>) ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 9
                        () -> {
                            final Defaults defaults = new DashaDefaults();
                            final int size = defaults.defaultCollectionSize();
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .componentFiller((Filler<OneField>) ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 10
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .componentFiller((Filler<OneField>) ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 11
                        () -> {
                            final Defaults defaults = new DashaDefaults();
                            final int size = defaults.defaultCollectionSize();
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 12
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 13
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .size(size)
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 14
                        () -> {
                            final Defaults defaults = new DashaDefaults();
                            final int size = defaults.defaultCollectionSize();
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 15
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<OneField> values = IntStream.range(0, size)
                                    .mapToObj(i -> new OneField(RandomStringUtils.randomAlphabetic(10)))
                                    .collect(Collectors.toSet());
                            final Iterator<OneField> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(OneField[].class)
                                                    .tail(
                                                            ArrayTails
                                                                    .<OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    OneField[].class,
                                    null,
                                    ArrayTailsTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        }
                )
                .map(Supplier::get);
    }

    protected static <T> Consumer<T[]> baseChecker(int size) {
        return array -> {
            Assertions.assertNotNull(array);
            Assertions.assertEquals(size, array.length);
        };
    }

    protected static <T> Consumer<T[]> componentTypeChecker(Class<T> componentClass) {
        return array -> {
            Assertions.assertNotNull(array);

            for (T component : array) {
                Assertions.assertEquals(componentClass, component.getClass());
            }
        };
    }

    protected static <T> Consumer<T[]> componentValueChecker(final T... values) {
        return componentValueChecker(Optional.ofNullable(values).map(Stream::of).orElse(Stream.empty()).collect(Collectors.toSet()));
    }

    protected static <T> Consumer<T[]> componentValueChecker(final Set<T> values) {
        return collection -> {
            Assertions.assertNotNull(collection);

            for (T component : collection) {
                Assertions.assertTrue(values.contains(component), "Invalid value: " + component);
            }
        };
    }
}
