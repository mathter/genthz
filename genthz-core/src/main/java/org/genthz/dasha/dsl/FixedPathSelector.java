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

import java.util.stream.Stream;

class FixedPathSelector extends PathSelector {
    private final String element;

    public FixedPathSelector(Selector parent, String element) {
        super(parent);
        this.element = element;
    }

    @Override
    public Stream<Parameter> params() {
        return Stream.concat(
                super.params(),
                Stream.of(Parameter.of("element", this.element))
        );
    }

    public String getElement() {
        return element;
    }

    @Override
    public boolean test(Context context) {
        boolean result;
        if ("/".equals(this.element)) {
            result = context.up() == null;
        } else if (context instanceof NodeInstanceContext) {
            result = this.element.equals(((NodeInstanceContext) context).node());
        } else {
            result = false;
        }

        return result && super.test(context);
    }
}
