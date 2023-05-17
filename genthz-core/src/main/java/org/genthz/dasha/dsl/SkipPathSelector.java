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
import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.function.Selector;

import java.util.stream.Stream;

class SkipPathSelector extends PathSelector {
    private final int skip;

    public SkipPathSelector(Selector parent, int skip) {
        super(parent);
        this.skip = skip;
        this.metric(skip);
    }

    @Override
    public Stream<Parameter> params() {
        return Stream.concat(
                super.params(),
                Stream.of(Parameter.of("skip", this.getSkip()))
        );
    }

    public int getSkip() {
        return skip;
    }

    @Override
    public boolean test(Context context) {
        final boolean result;
        final InstanceContext[] ups = context.ups()
                .filter(e -> e instanceof InstanceContext)
                .toArray(i -> new InstanceContext[i]);

        if (ups.length >= this.skip) {
            result = this.up().map(e -> e.test(ups[ups.length - this.skip])).orElse(false);
        } else {
            result = false;
        }

        return result;
    }
}
