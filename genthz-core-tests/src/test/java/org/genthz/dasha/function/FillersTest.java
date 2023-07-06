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

import org.genthz.FieldMatchers;
import org.genthz.ObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.genthz.etalon.model.SimpleGeneric;
import org.genthz.function.FieldMatcher;
import org.genthz.reflection.Util;
import org.genthz.util.StreamUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FillersTest {
    @ParameterizedTest
    @MethodSource("data")
    public void testIncludesByStringArray(Collection<String> includes, Collection<String> excludes, Set<String> fiedldNames) {
        final ObjectFactory objectFactory = new DashaDsl() {
            {
                this.def();

                if (includes != null) {
                    this.unstrict(SimpleGeneric.class)
                            .filler(Fillers.includes(includes.stream().toArray(String[]::new)));
                } else if (excludes != null) {
                    this.unstrict(SimpleGeneric.class)
                            .filler(Fillers.excludes(excludes.stream().toArray(String[]::new)));
                }
            }
        }.objectFactory();
        final SimpleGeneric instance = objectFactory.get(SimpleGeneric.class, String.class, Integer.class);

        Assertions.assertNotNull(instance);

        for (Field field : StreamUtil.of((Class) SimpleGeneric.class, c -> c.getSuperclass())
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .collect(Collectors.toList())) {
            if (fiedldNames.contains(field.getName())) {
                Assertions.assertNotNull(Util.getFieldValue(field, instance));
            } else {
                Assertions.assertNull(Util.getFieldValue(field, instance));
            }
        }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testIncludesByFieldMatcherArray(Collection<String> includes, Collection<String> excludes, Set<String> fiedldNames) {
        final ObjectFactory objectFactory = new DashaDsl() {
            {
                this.def();

                if (includes != null) {
                    this.unstrict(SimpleGeneric.class)
                            .filler(Fillers.includes(includes.stream()
                                    .map(FieldMatchers::name)
                                    .toArray(FieldMatcher[]::new)));
                } else if (excludes != null) {
                    this.unstrict(SimpleGeneric.class)
                            .filler(Fillers.excludes(excludes.stream()
                                    .map(FieldMatchers::name)
                                    .toArray(FieldMatcher[]::new)));
                }
            }
        }.objectFactory();
        final SimpleGeneric instance = objectFactory.get(SimpleGeneric.class, String.class, Integer.class);

        Assertions.assertNotNull(instance);

        for (Field field : StreamUtil.of((Class) SimpleGeneric.class, c -> c.getSuperclass())
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .collect(Collectors.toList())) {
            if (fiedldNames.contains(field.getName())) {
                Assertions.assertNotNull(Util.getFieldValue(field, instance));
            } else {
                Assertions.assertNull(Util.getFieldValue(field, instance));
            }
        }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testIncludesByFieldMatcherCollection(Collection<String> includes, Collection<String> excludes, Set<String> fiedldNames) {
        final ObjectFactory objectFactory = new DashaDsl() {
            {
                this.def();

                if (includes != null) {
                    this.unstrict(SimpleGeneric.class)
                            .filler(Fillers.includes(includes.stream()
                                    .map(e -> (FieldMatcher) FieldMatchers.name(e))
                                    .collect(Collectors.toList())));
                } else if (excludes != null) {
                    this.unstrict(SimpleGeneric.class)
                            .filler(Fillers.excludes(excludes.stream()
                                    .map(e -> (FieldMatcher) FieldMatchers.name(e))
                                    .collect(Collectors.toList())));
                }
            }
        }.objectFactory();
        final SimpleGeneric instance = objectFactory.get(SimpleGeneric.class, String.class, Integer.class);

        Assertions.assertNotNull(instance);

        for (Field field : StreamUtil.of((Class) SimpleGeneric.class, c -> c.getSuperclass())
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .collect(Collectors.toList())) {
            if (fiedldNames.contains(field.getName())) {
                Assertions.assertNotNull(Util.getFieldValue(field, instance));
            } else {
                Assertions.assertNull(Util.getFieldValue(field, instance));
            }
        }
    }

    public static Stream<Arguments> data() {
        final String[] fieldNames = {"stringField", "dateField", "tField", "collectionField", "listField", "setField", "mapField", "arrayField"};
        return Stream.concat(
                Stream.of(
                        Arguments.of(
                                null,
                                null,
                                Stream.of(fieldNames).collect(Collectors.toSet())
                        ),
                        Arguments.of(
                                Collections.emptyList(),
                                null,
                                Collections.emptySet()
                        ),
                        Arguments.of(
                                null,
                                Collections.emptyList(),
                                Stream.of(fieldNames).collect(Collectors.toSet())
                        )
                ),
                Stream.concat(
                        Stream.of(fieldNames)
                                .reduce(
                                        new ArrayList<Set<String>>(),
                                        (l, r) -> {
                                            final Set<String> max = l.stream().max(Comparator.comparingInt(Set::size)).orElse(new HashSet<>());
                                            final Set<String> next = new HashSet<String>();

                                            next.addAll(max);
                                            next.add(r);

                                            l.add(next);
                                            return l;
                                        },
                                        (l, r) -> {
                                            l.addAll(r);
                                            return l;
                                        }
                                )
                                .stream()
                                .map(e -> Arguments.of(
                                                e,
                                                null,
                                                e
                                        )
                                ),
                        Stream.of(fieldNames)
                                .reduce(
                                        new ArrayList<Set<String>>(),
                                        (l, r) -> {
                                            final Set<String> max = l.stream().max(Comparator.comparingInt(Set::size)).orElse(new HashSet<>());
                                            final Set<String> next = new HashSet<String>();

                                            next.addAll(max);
                                            next.add(r);

                                            l.add(next);
                                            return l;
                                        },
                                        (l, r) -> {
                                            l.addAll(r);
                                            return l;
                                        }
                                )
                                .stream()
                                .map(e -> Arguments.of(
                                                null,
                                                e,
                                                Optional.of(Stream.of(fieldNames).collect(Collectors.toSet()))
                                                        .map(ee -> {
                                                            ee.removeAll(e);
                                                            return ee;
                                                        })
                                                        .get()
                                        )
                                )
                )
        );
    }
}
