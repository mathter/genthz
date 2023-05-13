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

import org.genthz.dsl.Metric;
import org.genthz.function.Selector;

import java.util.Optional;

abstract class SelectorOp<THIS extends SelectorOp<THIS>> extends Op<SelectorOp<?>> implements Metric<THIS> {

    private Optional<Integer> metric = Optional.empty();

    public SelectorOp(SelectorOp up) {
        super(up);
    }

    @Override
    public int metric() {
        return this.metric.orElse(0);
    }

    @Override
    public THIS metric(int mertic) {
        this.metric = Optional.of(mertic);
        return (THIS) this;
    }

    public <T extends Metric> T setTo(T metric) {
        this.metric.ifPresent(e -> metric.m(e));

        return metric;
    }

    @Override
    public int compareTo(Metric<THIS> o) {
        throw new UnsupportedOperationException();
    }

    public abstract Selector selector();
}