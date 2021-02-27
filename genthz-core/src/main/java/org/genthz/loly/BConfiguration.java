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
import org.genthz.configuration.dsl.Custom;
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
        final Selector selector = selector(selectable.selector());
        final Object function;

        if (selectable instanceof org.genthz.configuration.dsl.DefaultFiller) {
            result = Triple.of(selector, null, BConfiguration.build((org.genthz.configuration.dsl.DefaultFiller) selectable));
        } else {
            function = selectable.function();

            if (function instanceof InstanceBuilder) {
                if (selectable.isSimple()) {
                    result = Triple.of(selector, (InstanceBuilder) function, new UnitFiller());
                } else {
                    result = Triple.of(selector, (InstanceBuilder) function, null);
                }
            } else if (function instanceof Filler) {
                if (selectable.isSimple()) {
                    result = Triple.of(selector, null, new UnitFiller());
                } else {
                    result = Triple.of(selector, null, (Filler) function);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        return result;
    }

    private static Selector selector(org.genthz.configuration.dsl.Selector selector) {
        return selector != null ? selector(
                selector,
                selector(
                        selector.next()
                )
        ) : null;
    }

    private static Selector selector(org.genthz.configuration.dsl.Selector selector, Selector next) {
        final Selector result;

        if (selector instanceof Strict) {
            result = new StrictClassSelector(selector.name(), selector.metrics(), next, ((Strict<?>) selector).clazz());
        } else if (selector instanceof NonStrict) {
            result = new NonStrictClassSelector(selector.name(), selector.metrics(), next, ((NonStrict<?>) selector).clazz());
        } else if (selector instanceof Custom) {
            result = new CustomSelector(selector.name(), selector.metrics(), next, (Custom) selector);
        } else if (selector instanceof Path) {
            result = PathSelectorBuilder.build(selector, next);
        } else {
            throw new IllegalArgumentException("'" + selector + "' is illegal selector!");
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
}
