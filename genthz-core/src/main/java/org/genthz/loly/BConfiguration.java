/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
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
package org.genthz.loly;

import org.apache.commons.lang3.tuple.Triple;
import org.genthz.Filler;
import org.genthz.InstanceBuilder;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.ConstructorBasedInstanceBuilder;
import org.genthz.configuration.dsl.Custom;
import org.genthz.configuration.dsl.FunctionalFiller;
import org.genthz.configuration.dsl.FunctionalInstanceBuilder;
import org.genthz.configuration.dsl.NegateSelector;
import org.genthz.configuration.dsl.NonStrict;
import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Selectable;
import org.genthz.configuration.dsl.Strict;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

final class BConfiguration {

    private final Collection<Selector> instanceBuilderSelectors;

    private final Map<Selector, InstanceBuilder<?>> instanceBuilderMap;

    private final Collection<Selector> fillerSelectors;

    private final Map<Selector, Filler<?>> fillerMap;

    private BConfiguration(
            Map<Selector, InstanceBuilder<?>> instanceBuilderMap,
            Map<Selector, Filler<?>> fillerMap
    ) {
        this.instanceBuilderMap = Collections.unmodifiableMap(Objects.requireNonNull(instanceBuilderMap));
        this.instanceBuilderSelectors = Collections.unmodifiableCollection(this.instanceBuilderMap.keySet());

        this.fillerMap = Collections.unmodifiableMap(Objects.requireNonNull(fillerMap));
        this.fillerSelectors = Collections.unmodifiableCollection(this.fillerMap.keySet());
    }

    public static BConfiguration build(Configuration configuration) {
        final Map<Selector, InstanceBuilder<?>> instanceBuilderMap = new HashMap<>();
        final Map<Selector, Filler<?>> fillerMap = new HashMap<>();

        Objects.requireNonNull(configuration)
                .selectables()
                .stream()
                .map(BConfiguration::selectable)
                .forEach(e -> {
                    final Selector selector = e.getLeft();
                    final InstanceBuilder<?> instanceBuilder = e.getMiddle();
                    final Filler<?> filler = e.getRight();

                    if (instanceBuilder != null) {
                        instanceBuilderMap.put(selector, instanceBuilder);
                    }

                    if (filler != null) {
                        fillerMap.put(selector, filler);
                    }
                });

        return new BConfiguration(
                instanceBuilderMap,
                fillerMap
        );
    }

    private static Triple<Selector, InstanceBuilder, Filler> selectable(Selectable selectable) {
        final Triple<Selector, InstanceBuilder, Filler> result;
        final Selector selector = selector(selectable.selector()).get();

        if (selectable instanceof org.genthz.configuration.dsl.DefaultFiller) {
            result = Triple.of(selector, null, BConfiguration.build((org.genthz.configuration.dsl.DefaultFiller) selectable));
        } else if (selectable instanceof org.genthz.configuration.dsl.CollectionFiller) {
            result = Triple.of(selector, null, BConfiguration.build((org.genthz.configuration.dsl.CollectionFiller) selectable));
        } else {
            if (selectable instanceof FunctionalInstanceBuilder) {
                if (selectable.isSimple()) {
                    result = Triple.of(selector, ((FunctionalInstanceBuilder<?>) selectable).function(), new UnitFiller());
                } else {
                    result = Triple.of(selector, ((FunctionalInstanceBuilder<?>) selectable).function(), null);
                }
            } else if (selectable instanceof ConstructorBasedInstanceBuilder) {
                result = Triple.of(
                        selector,
                        BConfiguration.build((ConstructorBasedInstanceBuilder) selectable),
                        selectable.isSimple() ? new UnitFiller() : null
                );
            } else if (selectable instanceof FunctionalFiller) {
                if (selectable.isSimple()) {
                    result = Triple.of(selector, null, new UnitFiller());
                } else {
                    result = Triple.of(selector, null, ((FunctionalFiller<?>) selectable).function());
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        return result;
    }

    private static Optional<Selector> selector(org.genthz.configuration.dsl.Selector selector) {
        return selector != null ? selector(
                selector,
                selector(
                        selector.next()
                )
        ) : Optional.empty();
    }

    private static Optional<Selector> selector(org.genthz.configuration.dsl.Selector selector, Optional<Selector> next) {
        final Selector result;

        if (selector instanceof Strict) {
            result = buildStrict(selector, next);
        } else if (selector instanceof NonStrict) {
            result = buildNonStrict(selector, next);
        } else if (selector instanceof Custom) {
            result = buildCustom(selector, next);
        } else if (selector instanceof Path) {
            result = PathSelectorBuilder.build(selector, next);
        } else if (selector instanceof NegateSelector) {
            result = buildNot(selector, next);
        } else {
            throw new IllegalArgumentException("'" + selector + "' is illegal selector!");
        }

        return Optional.ofNullable(result);
    }

    private static Selector buildStrict(org.genthz.configuration.dsl.Selector selector, Optional<Selector> next) {
        return new StrictClassSelector(selector.name(), selector.metrics(), next, ((Strict<?>) selector).clazz());
    }

    private static Selector buildNonStrict(org.genthz.configuration.dsl.Selector selector, Optional<Selector> next) {
        return new NonStrictClassSelector(selector.name(), selector.metrics(), next, ((NonStrict<?>) selector).clazz());
    }

    private static Selector buildCustom(org.genthz.configuration.dsl.Selector selector, Optional<Selector> next) {
        return new CustomSelector(selector.name(), selector.metrics(), next, (Custom) selector);
    }

    private static Selector buildNot(org.genthz.configuration.dsl.Selector selector, Optional<Selector> next) {
        final Selector result;
        final Selector origin = selector(((NegateSelector) selector).origin(), next).get();
        final boolean negateChain = ((NegateSelector) selector).negateChain();

        if (negateChain) {
            result = new org.genthz.loly.NegateSelector(
                    selector.name(),
                    selector.metrics(),
                    Optional.empty(),
                    origin.negate()
            );
        } else {
            result = new org.genthz.loly.NegateSelector(
                    selector.name(),
                    selector.metrics(),
                    origin.next(),
                    origin.predicate().negate()
            );
        }

        return result;
    }

    private static <T> DefaultFiller<T> build(org.genthz.configuration.dsl.DefaultFiller defaultFiller) {
        return new DefaultFillerWithCustom(
                defaultFiller.included() != null ? Arrays.asList(defaultFiller.included()) : null,
                defaultFiller.excluded() != null ? Arrays.asList(defaultFiller.excluded()) : null,
                defaultFiller.custom()
        );
    }

    private static <T, C> CollectionDefaultFiller build(org.genthz.configuration.dsl.CollectionFiller collectionFiller) {
        return new CollectionDefaultFiller<T, C>(
                collectionFiller.collectionClass(),
                collectionFiller.componentClass(),
                collectionFiller.custom(),
                collectionFiller.componentCustom(),
                collectionFiller.count()
        );
    }

    private static <T> org.genthz.InstanceBuilder<T> build(ConstructorBasedInstanceBuilder<T> selectable) {
        return new org.genthz.loly.CalculatedConstructorBasedInstanceBuilder(selectable.predicate(), selectable.description());
    }

    public Stream<Selector> getInstanceBuilderSelectors() {
        return this.instanceBuilderSelectors.stream();
    }

    public Stream<Selector> getFillerSelectors() {
        return this.fillerSelectors.stream();
    }

    public <T> InstanceBuilder<T> getInstanceBuilder(Selector selector) {
        return (InstanceBuilder<T>) this.instanceBuilderMap.get(selector);
    }

    public <T> Filler<T> getFiller(Selector selector) {
        return (Filler<T>) this.fillerMap.get(selector);
    }
}
