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
import org.genthz.dsl.Metric;
import org.genthz.function.Selector;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractSelector implements Selector {
    private final Optional<Selector> up;

    private String name = NameGenerator.next();

    private int metric = 1;

    protected AbstractSelector(Selector up) {
        this.up = Optional.ofNullable(up);
    }

    protected Stream<Pair<String, Object>> params() {
        return Stream.of(
                Pair.of("name", this.name),
                Pair.of("metric", this.metric),
                Pair.of("effective", this.effective())
        );
    }

    @Override
    public Optional<Selector> up() {
        return this.up;
    }

    @Override
    public int compareTo(Metric o) {
        return this.effective() - o.effective();
    }

    @Override
    public int metric() {
        return this.metric;
    }

    @Override
    public int effective() {
        return this.metric + this.up.map(e -> e.effective()).orElse(0);
    }

    @Override
    public AbstractSelector metric(int mertic) {
        this.metric = mertic;
        return this;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public AbstractSelector name(CharSequence name) {
        this.name = name != null ?
                name instanceof String
                        ? (String) name
                        : name.toString()
                : NameGenerator.next();

        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName())
                .append('{')
                .append(
                        this.params()
                                .map(e -> e.getLeft() + "=" + e.getRight())
                                .collect(Collectors.joining(","))
                )
                .append('}');

        return sb.toString();
    }
}
