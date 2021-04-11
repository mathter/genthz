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

abstract class ClassSelector<T> extends Selector implements org.genthz.configuration.dsl.Selector {

    private final Class<T> clazz;

    protected ClassSelector(Dsl dsl, String name, Selector selector, Class<T> clazz) {
        super(dsl, name, org.genthz.configuration.dsl.Selector.METRICS_UNIT, selector);
        this.clazz = clazz;
    }

    public Class<T> clazz() {
        return this.clazz;
    }

    static class Strict<T> extends ClassSelector implements org.genthz.configuration.dsl.Strict {
        Strict(Dsl dsl, String name, Selector next, Class<T> clazz) {
            super(dsl, name, next, clazz);
        }
    }

    static class NonStrict<T> extends ClassSelector implements org.genthz.configuration.dsl.NonStrict<T> {
        NonStrict(Dsl dsl, String name, Selector next, Class<T> clazz) {
            super(dsl, name, next, clazz);
        }
    }


}
