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
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.Selector;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InstanceBuilderThenOp<T> extends Op<SelectorOp<?>> implements InstanceBuilderThen<T> {
    private final Filler<?> fillerFunction;

    private InstanceBuilderConsumer<?> function;

    public InstanceBuilderThenOp(SelectorOp up, Filler<?> fillerFunction) {
        super(up);
        this.fillerFunction = fillerFunction;
    }

    @Override
    public void instanceBuilder(InstanceBuilderConsumer<T> function) {
        this.function = function;
        this.dsl().reg(this);
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        final Selector selector = this.up().selector();

        return Stream.of(
                Pair.of(selector, this.fillerFunction),
                Pair.of(selector, this.function)
        ).collect(Collectors.toList());
    }
}
