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
import org.genthz.function.Filler;
import org.genthz.configuration.dsl.Selector;

class DefaultFiller<T> extends Selectable<T> implements Filler<T>, org.genthz.configuration.dsl.DefaultFiller<T> {

    public DefaultFiller(Selector<T> selector) {
        super(selector);
    }

    @Override
    public T apply(Context<T> context, T t) {
        return null;
    }

    @Override
    public org.genthz.configuration.dsl.DefaultFiller<T> including(String... field) {
        return null;
    }

    @Override
    public org.genthz.configuration.dsl.DefaultFiller<T> excluding(String... field) {
        return null;
    }

    @Override
    public String[] included() {
        return new String[0];
    }

    @Override
    public String[] excluded() {
        return new String[0];
    }

    @Override
    public org.genthz.configuration.dsl.DefaultFiller<T> custom(Filler<T> function) {
        return null;
    }

    @Override
    public Filler<?> custom() {
        return null;
    }
}
