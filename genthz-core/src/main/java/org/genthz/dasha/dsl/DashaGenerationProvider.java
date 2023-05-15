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
package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.Defaults;
import org.genthz.GenerationProvider;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.DashaDefaults;
import org.genthz.function.DefaultFiller;
import org.genthz.function.DefaultInstanceBuilder;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

class DashaGenerationProvider implements GenerationProvider {
    private static final Comparator<Pair<Selector, ?>> COMPARATOR = Comparator.comparingInt(e -> e.getLeft().effective());

    private final Optional<GenerationProvider> up;

    private final Defaults defaults;

    private final Collection<Pair<Selector, InstanceBuilder>> instanceBuilders;

    private final Collection<Pair<Selector, Filler>> filles;

    public DashaGenerationProvider(
            Collection<Pair<Selector, InstanceBuilder>> instanceBuilders,
            Collection<Pair<Selector, Filler>> filles) {
        this(null, null, instanceBuilders, filles);
    }

    public DashaGenerationProvider(
            GenerationProvider up,
            Defaults defaults,
            Collection<Pair<Selector, InstanceBuilder>> instanceBuilders,
            Collection<Pair<Selector, Filler>> filles) {
        this.up = Optional.ofNullable(up);
        this.defaults = defaults != null ? defaults : new DashaDefaults();
        this.instanceBuilders = Objects.requireNonNull(instanceBuilders);
        this.filles = Objects.requireNonNull(filles);
    }

    @Override
    public <T> InstanceBuilder<T> instanceBuilder(InstanceContext context) {
        return this.instanceBuilders.stream()
                .filter(e -> e.getLeft().test(context))
                .max(COMPARATOR)
                .map(Pair::getRight)
                .orElseGet(() -> this.up.map(e -> e.instanceBuilder(context)).orElseGet(() -> new DefaultInstanceBuilder<>()));
    }

    @Override
    public <T> Filler<T> filler(InstanceContext context) {
        return this.filles.stream()
                .filter(e -> e.getLeft().test(context))
                .max(COMPARATOR)
                .map(Pair::getRight)
                .orElseGet(() -> this.up.map(e -> e.filler(context)).orElseGet(() -> new DefaultFiller()));
    }

    @Override
    public Defaults defaults() {
        return this.defaults;
    }

    @Override
    public Optional<GenerationProvider> up() {
        return this.up;
    }
}
