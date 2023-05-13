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

import java.util.Optional;
import java.util.regex.Pattern;

class PatternPathSelector extends PathSelector {
    private final Pattern pattern;

    public PatternPathSelector(Selector parent, Pattern pattern) {
        super(parent);
        this.pattern = pattern;
    }

    public PatternPathSelector(Selector parent, String pattern) {
        super(parent);
        this.pattern = Pattern.compile(pattern);
    }

    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public boolean test(Context context) {
        boolean result;

        if (context instanceof NodeInstanceContext) {
            // Check for root
            if ("/".equals(this.pattern.pattern())) {
                result = context.up() == null;
            } else {

                result = Optional.ofNullable(((NodeInstanceContext) context).node())
                        .filter(e -> e instanceof CharSequence)
                        .map(Object::toString)
                        .map(e -> this.pattern.matcher(e).matches())
                        .orElse(false);
            }
        } else {
            result = false;
        }

        return result && super.

                test(context);
    }
}
