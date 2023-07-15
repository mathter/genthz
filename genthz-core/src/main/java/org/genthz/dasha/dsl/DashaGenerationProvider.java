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
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;
import org.genthz.logging.Logger;
import org.genthz.logging.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

class DashaGenerationProvider implements GenerationProvider {
    private static final Logger LOG = LoggerFactory.get();
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
        final InstanceBuilder<T> result;
        final List<Pair<Selector, InstanceBuilder>> willBeUsed = this.instanceBuilders.stream()
                .filter(e -> e.getLeft().test(context))
                .sorted(COMPARATOR)
                .collect(Collectors.toList());
        final int size = willBeUsed.size();

        if (size == 0) {
            result = (InstanceBuilder<T>) this.up.map(e -> e.instanceBuilder(context))
                    .orElseThrow(() -> new IllegalStateException(
                            String.format("There are no instance builder for context %s", context)
                    ));
        } else {
            final Pair<Selector, InstanceBuilder> pair;
            if (size == 1) {
                pair = willBeUsed.get(0);
            } else {
                final Pair<Selector, InstanceBuilder> last = willBeUsed.get(size - 1);

                if (last.getLeft().effective() > willBeUsed.get(size - 2).getLeft().effective()) {
                    pair = last;
                } else {
                    throw new IllegalStateException(
                            String.format("There are more then one instance builder for context: %s with metric=%s selectors: %s",
                                    context,
                                    last.getLeft().effective(),
                                    willBeUsed
                            )
                    );
                }
            }

            LOG.logInstanceBuilderWillBeUsed(context, pair);
            result = pair.getRight();
        }

        return result;
    }

    @Override
    public <T> Filler<T> filler(InstanceContext context) {
        final Filler<T> result;
        final List<Pair<Selector, Filler>> willBeUsed = this.filles.stream()
                .filter(e -> e.getLeft().test(context))
                .sorted(COMPARATOR)
                .collect(Collectors.toList());
        final int size = willBeUsed.size();

        if (size == 0) {
            result = (Filler<T>) this.up.map(e -> e.filler(context))
                    .orElseThrow(() -> new IllegalStateException(
                            String.format("There are no filler for context %s", context)
                    ));
        } else {
            final Pair<Selector, Filler> pair;

            if (size == 1) {
                pair = willBeUsed.get(0);
            } else {
                final Pair<Selector, Filler> last = willBeUsed.get(size - 1);

                if (last.getLeft().effective() > willBeUsed.get(size - 2).getLeft().effective()) {
                    pair = last;
                } else {
                    throw new IllegalStateException(
                            String.format("There are more then one instance builder for context: %s with metric=%s selectors: %s",
                                    context,
                                    last.getLeft().effective(),
                                    willBeUsed
                            )
                    );
                }
            }

            LOG.logFillerBuilderWillBeUsed(context, pair);
            result = pair.getRight();
        }

        return result;
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
