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
import org.genthz.context.NodeInstanceContext;
import org.genthz.function.Selector;

import java.util.Objects;
import java.util.stream.Stream;

class IndexPathSelector extends PathSelector {
    private final int index;

    public IndexPathSelector(Selector parent, int index) {
        super(Objects.requireNonNull(parent));
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    protected Stream<Pair<String, Object>> params() {
        return Stream.concat(super.params(), Stream.of(Pair.of("index", this.getIndex())));
    }

    @Override
    public boolean test(Context context) {
        final boolean result;

        if (context instanceof NodeInstanceContext) {
            final Object node = ((NodeInstanceContext) context).node();
            if (node instanceof Integer) {
                return this.index == (Integer) node && super.test(context);
            } else {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }
}
