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

import org.genthz.Context;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.genthz.configuration.dsl.Selector.METRICS_ZERO;

abstract class Selector implements Predicate<Context<?>> {

    protected final Predicate<Context<?>> predicate;

    private final String name;

    private final Function<Context<?>, Long> metrics;

    protected final Optional<Selector> next;

    protected Selector(
            String name,
            Function<Context<?>, Long> metrics,
            Optional<Selector> next,
            Predicate<Context<?>> predicate
    ) {
        this.name = Objects.requireNonNull(name);
        this.metrics = metrics;
        this.next = Objects.requireNonNull(next);
        this.predicate = Objects.requireNonNull(predicate);
    }

    public String name() {
        return this.name;
    }

    public Function<Context<?>, Long> metrics() {
        return (c) -> this.next
                .map(Selector::metrics)
                .orElse(METRICS_ZERO)
                .apply(c) + this.metrics.apply(c);
    }

    public Optional<Selector> next() {
        return this.next;
    }

    public Predicate<Context<?>> predicate() {
        return this.predicate;
    }

    @Override
    public boolean test(Context<?> context) {
        return this.predicate.test(context) && this.next.map((s) -> s.test(context)).orElse(true);
    }
}
