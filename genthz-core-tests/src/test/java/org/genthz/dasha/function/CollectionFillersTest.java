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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionFillersTest implements ArgumentsProvider {

    @ParameterizedTest
    @DisplayName("Check CollectionFillers")
    @ArgumentsSource(CollectionFillersTest.class)
    public void test(Dsl dsl, Class<? extends Collection> clazz, Type[] genericTypes, Consumer<Collection> checker) {
        final ObjectFactory objectFactory = new DashaObjectFactory(dsl.build());
        final Collection instance = objectFactory.get(clazz, genericTypes);

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
                                            this.strict(List.class, String.class)
                                                    .filler(
                                                            CollectionFillers.size(size)
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{String.class},
                                    baseChecker(size)
                            );
                        },

                        //2
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<String> values = IntStream.range(0, size)
                                    .mapToObj(i -> RandomStringUtils.randomAlphabetic(10))
                                    .collect(Collectors.toSet());
                            final Iterator<String> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(List.class, String.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List>size(size)
                                                                    .componentInstanceBuilder(ctx -> iterator.next())
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{String.class},
                                    CollectionFillersTest.<String>baseChecker(size)
                                            .andThen(componentTypeChecker(String.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        // 3
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<Simple> values = IntStream.range(0, size)
                                    .mapToObj(i -> {
                                        final Simple tmp = new Simple();
                                        tmp.setStringField(RandomStringUtils.randomAlphabetic(10));
                                        return tmp;
                                    })
                                    .collect(Collectors.toSet());
                            final Iterator<Simple> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(List.class, Simple.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List>size(size)
                                                                    .<Simple>componentInstanceBuilder(ctx -> new Simple())
                                                                    .componentFiller(ctx -> ctx.get().setStringField(iterator.next().getStringField()))
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{Simple.class},
                                    CollectionFillersTest.<Simple>baseChecker(size)
                                            .andThen(componentTypeChecker(Simple.class))
                                            .andThen(componentValueChecker(values))
                            );
                        },

                        //4
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<Simple> values = IntStream.range(0, size)
                                    .mapToObj(i -> {
                                        final Simple tmp = new Simple();
                                        tmp.setStringField(RandomStringUtils.randomAlphabetic(10));
                                        return tmp;
                                    })
                                    .collect(Collectors.toSet());
                            final Iterator<Simple> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(List.class, Simple.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List>size(size)
                                                                    .<Simple>componentFiller(ctx -> ctx.get().setStringField(iterator.next().getStringField()))
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{Simple.class},
                                    CollectionFillersTest.<Simple>baseChecker(size)
                                            .andThen(componentTypeChecker(Simple.class))
                                            .andThen(componentValueChecker(values)));
                        },

                        // 5
                        () -> {
                            final int size = RandomUtils.nextInt(10, 100);
                            final Set<Simple> values = IntStream.range(0, size)
                                    .mapToObj(i -> {
                                        final Simple tmp = new Simple();
                                        tmp.setStringField(RandomStringUtils.randomAlphabetic(10));
                                        return tmp;
                                    })
                                    .collect(Collectors.toSet());
                            final Iterator<Simple> iterator = values.iterator();

                            return Arguments.of(
                                    new DashaDsl() {
                                        {
                                            this.strict(List.class, Simple.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List>size(size)
                                                                    .<Simple>componentFiller(ctx -> ctx.get().setStringField(iterator.next().getStringField()))
                                                                    .componentInstanceBuilder(ctx -> new Simple())
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{Simple.class},
                                    CollectionFillersTest.<Simple>baseChecker(size)
                                            .andThen(componentTypeChecker(Simple.class))
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .componentSimple(ctx -> new OneField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentSimple(ctx -> new OneField(iterator.next().getField()))
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .size(size)
                                                                    .componentFiller((Filler<OneField>) ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .componentFiller((Filler<OneField>) ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentInstanceBuilder(ctx -> new OneField())
                                                                    .componentFiller((Filler<OneField>) ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .size(size)
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
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
                                            this.strict(List.class, OneField.class)
                                                    .filler(
                                                            CollectionFillers
                                                                    .<List, OneField>componentFiller(ctx -> ctx.get().setField(iterator.next().getField()))
                                                                    .componentInstanceBuilder(ctx -> new OneField())
                                                                    .size(size)
                                                    );
                                        }
                                    }.def(),
                                    List.class,
                                    new Type[]{OneField.class},
                                    CollectionFillersTest.<OneField>baseChecker(size)
                                            .andThen(componentTypeChecker(OneField.class))
                                            .andThen(componentValueChecker(values))
                            );
                        }
                )
                .map(Supplier::get);
    }

    protected static <T> Consumer<Collection<T>> baseChecker(int size) {
        return collection -> {
            Assertions.assertNotNull(collection);
            Assertions.assertEquals(size, collection.size());
        };
    }

    protected static <T> Consumer<Collection<T>> componentTypeChecker(Class<T> componentClass) {
        return collection -> {
            Assertions.assertNotNull(collection);

            for (T component : collection) {
                Assertions.assertEquals(componentClass, component.getClass());
            }
        };
    }

    protected static <T> Consumer<Collection<T>> componentValueChecker(final T... values) {
        return componentValueChecker(Optional.ofNullable(values).map(Stream::of).orElse(Stream.empty()).collect(Collectors.toSet()));
    }

    protected static <T> Consumer<Collection<T>> componentValueChecker(final Set<T> values) {
        return collection -> {
            Assertions.assertNotNull(collection);

            for (T component : collection) {
                Assertions.assertTrue(values.contains(component), "Invalid value: " + component);
            }
        };
    }
}
