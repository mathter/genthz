/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2021 mathter@mail.ru
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
package org.genthz.summer.configuration.dsl;

import org.genthz.context.Context;

import java.util.function.Predicate;

class UpSelector<T> extends Selector<T> {
    public UpSelector(Dsl dsl, Predicate<Context<?>> predicate, Selector<?> next) {
        super(dsl, predicate, next);
    }

    @Override
    public boolean test(Context<?> context) {
        return this.predicate.test(context) && (this.next == null || context.parent().map(e -> this.next.test(e)).orElse(false));
    }
}
