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

import org.genthz.configuration.dsl.Path;
import org.genthz.configuration.dsl.Selector;
import org.genthz.context.context.Context;

import java.util.function.Function;

class PathSkipElementSelector<T> extends AbstractSelector<T> implements Path<T> {
    private final int count;

    public PathSkipElementSelector(Selector<?> prev, int count) {
        super(prev);
        this.count = count;
    }

    @Override
    public Function<Context<?>, Integer> metrics() {
        return super.metrics() != null ? super.metrics() : c -> this.count;
    }

    @Override
    public boolean test(Context<?> context) {
        int test = this.count;

        while (context != null && test > 0) {
            context = context.parent();
            test--;
        }

        return test == 0 && super.test(context);
    }
}
