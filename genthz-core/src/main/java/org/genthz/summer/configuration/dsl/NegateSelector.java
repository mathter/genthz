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

class NegateSelector<T> extends Selector<T> implements org.genthz.configuration.dsl.NegateSelector<T> {
    private final Selector<T> origin;

    public NegateSelector(Dsl dsl,Predicate<Context<?>> predicate, Selector<?> next, Selector<T> origin) {
        super(dsl, predicate, next);
        this.origin = origin;
    }

    @Override
    public org.genthz.configuration.dsl.Selector<T> origin() {
        return this.origin;
    }

    @Override
    public boolean negateChain() {
        return false;
    }
}
