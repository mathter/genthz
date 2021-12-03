/*
 * GenThz - testing becomes easier
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
package org.genthz.configuration.dsl.polina;

import org.genthz.context.context.Context;
import org.genthz.configuration.dsl.Selector;

import java.util.function.Function;

abstract class AbstractSelector<T> extends Abstract<T> implements org.genthz.configuration.dsl.Selector<T> {
    protected final Selector<?> prev;

    private Function<Context<?>, Integer> metrics;

    public AbstractSelector(Selector<?> prev) {
        this.prev = prev;
        this.metrics = METRICS_ONE;
    }

    @Override
    public org.genthz.configuration.dsl.Selector<T> metrics(Function<Context<?>, Integer> function) {
        this.metrics = function;

        return this;
    }

    public Function<Context<?>, Integer> metrics() {
        return metrics;
    }

    @Override
    protected Selector<T> selector() {
        return this;
    }

    @Override
    public boolean test(Context<?> context) {
        return this.prev != null ? this.prev.test(context) : true;
    }
}
