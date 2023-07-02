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

import org.genthz.context.Context;
import org.genthz.context.NodeInstanceContext;
import org.genthz.function.Selector;

import java.util.Objects;
import java.util.stream.Stream;

class IndexPathSelector extends PathSelector {
    private final int index;

    private final boolean isConstructorParameter;

    public IndexPathSelector(Selector parent, int index) {
        this(parent, index, false);
    }

    public IndexPathSelector(Selector parent, int index, boolean isConstructorParameter) {
        super(parent);
        this.index = index;
        this.isConstructorParameter = isConstructorParameter;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Stream<Parameter> params() {
        return Stream.concat(
                super.params(),
                Stream.of(Parameter.of("index", this.getIndex()))
        );
    }

    @Override
    public boolean test(Context context) {
        final boolean result;

        if (context instanceof NodeInstanceContext) {
            final NodeInstanceContext ctx = (NodeInstanceContext) context;
            final Object node = ctx.node();
            if (node instanceof Integer) {
                return this.index == (Integer) node && ctx.isConstructorParameter() == this.isConstructorParameter && super.test(context);
            } else {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }
}
