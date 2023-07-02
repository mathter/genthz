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
package org.genthz.dasha.context;

import org.genthz.FieldMatchers;
import org.genthz.context.AccessorResolver;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Node;
import org.genthz.etalon.model.SimpleGeneric;
import org.genthz.function.FieldMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DashaAccessorResolverTest {
    @ParameterizedTest
    @MethodSource("data")
    public void test(Collection<FieldMatcher> includes, Collection<FieldMatcher> excludes, Set<String> fiedldNames) {
        final ContextFactory contextFactory = new DashaContextFactory();
        final AccessorResolver accessorResolver = new DashaAccessorResolver(includes, excludes);
        final InstanceContext context = contextFactory.single(SimpleGeneric.class, String.class, Integer.class);

        final Collection<? extends Node<String>> nodes = accessorResolver.resolve(context);

        Assertions.assertNotNull(nodes);
        Assertions.assertEquals(fiedldNames.size(), nodes.size());

        for (Node node : nodes) {
            Assertions.assertTrue(fiedldNames.remove(node.node()));
        }

        Assertions.assertTrue(fiedldNames.isEmpty());
    }

    public static Stream<Arguments> data() {
        final String[] fieldNames = {"stringField", "dateField", "tField", "collectionField", "listField", "setField", "mapField", "arrayField"};
        return Stream.concat(
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
                                        e.stream().map(ee -> FieldMatchers.name(ee)).collect(Collectors.toList()),
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
                                        e.stream().map(ee -> FieldMatchers.name(ee)).collect(Collectors.toList()),
                                        Optional.of(Stream.of(fieldNames).collect(Collectors.toSet()))
                                                .map(ee -> {
                                                    ee.removeAll(e);
                                                    return ee;
                                                })
                                                .get()
                                )
                        )
        );
    }
}
