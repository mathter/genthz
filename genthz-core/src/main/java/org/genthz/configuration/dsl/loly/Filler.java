/*
 * Generated - testing becomes easier
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
package org.genthz.configuration.dsl.loly;

import org.genthz.configuration.dsl.SimpleSelectableException;

class Filler<T> extends Selectable<T> {

    final org.genthz.Filler<T> function;

    Filler(org.genthz.Filler<T> function, Selector selector) {
        super(selector);
        this.function = function;
    }

    @Override
    public org.genthz.Filler<?> function() {
        return this.function;
    }

    @Override
    public void simple() {
        throw new SimpleSelectableException(this);
    }
}
