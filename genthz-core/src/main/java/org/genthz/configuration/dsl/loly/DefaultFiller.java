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

import org.genthz.Filler;

class DefaultFiller<T> extends Selectable implements org.genthz.configuration.dsl.DefaultFiller<T> {
    private String[] includedFieldNames;

    private String[] excludedFieldNames;

    private org.genthz.Filler<?> function;

    public DefaultFiller(Selector selector) {
        super(selector);
    }

    @Override
    public DefaultFiller including(String... field) {
        this.includedFieldNames = field;
        this.excludedFieldNames = null;

        return this;
    }

    @Override
    public DefaultFiller excluding(String... field) {
        this.excludedFieldNames = field;
        this.includedFieldNames = null;

        return this;
    }

    @Override
    public String[] included() {
        return this.includedFieldNames;
    }

    @Override
    public String[] excluded() {
        return this.excludedFieldNames;
    }

    @Override
    public DefaultFiller<T> custom(org.genthz.Filler<T> function) {
        this.function = function;

        return this;
    }

    @Override
    public Filler<?> custom() {
        return this.function != null ? this.function : null;
    }
}
