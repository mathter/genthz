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
import org.genthz.configuration.Filler;
import org.genthz.configuration.dsl.Selector;

class FillerSelectable<T> extends AbstractSelectable<T> implements
        org.genthz.configuration.dsl.FillerSelectable<T>,
        Filler<T> {
    private final Filler<T> function;

    public FillerSelectable(Selector<T> selector, Filler<T> function) {
        super(selector);
        this.function = function;
    }

    @Override
    public FillerSelectable<T> f(Filler<T> filler) {
        throw new IllegalStateException();
    }

    @Override
    public T apply(Context<T> context, T value) {
        return this.function.apply(context, value);
    }
}
