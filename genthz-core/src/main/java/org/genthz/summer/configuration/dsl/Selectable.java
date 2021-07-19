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

import org.genthz.Description;
import org.genthz.configuration.dsl.Selector;
import org.genthz.configuration.dsl.SimpleSelectableException;

abstract class Selectable<T> implements org.genthz.configuration.dsl.Selectable<T> {
    private final Selector<T> selector;

    public Selectable(Selector<T> selector) {
        this.selector = selector;
    }

    @Override

    public Description description() {
        return null;
    }

    @Override
    public Selector<T> selector() {
        return this.selector;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Selectable<T> name(String name) {
        return this;
    }

    @Override
    public org.genthz.configuration.dsl.Selectable simple() {
        return null;
    }

    @Override
    public boolean isSimple() throws SimpleSelectableException {
        return false;
    }
}
