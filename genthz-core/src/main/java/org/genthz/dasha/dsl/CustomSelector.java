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
import org.genthz.function.Selector;

import java.util.Objects;
import java.util.function.Predicate;

class CustomSelector extends AbstractSelector {
    private final Predicate<Context> predicate;

    public CustomSelector(Selector parent, Predicate<? extends Context> predicate) {
        super(parent);
        this.predicate = (Predicate<Context>) Objects.requireNonNull(predicate);
    }

    @Override
    public boolean test(Context context) {
        return this.predicate.test(context);
    }
}
