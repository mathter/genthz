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

import java.lang.reflect.Constructor;
import java.util.function.Predicate;

class ConstructorBasedInstanceBuilder<T> extends Selectable implements org.genthz.configuration.dsl.ConstructorBasedInstanceBuilder<T> {

    private final Predicate<Constructor<T>> predicate;

    public ConstructorBasedInstanceBuilder(Selector selector, Predicate<Constructor<T>> predicate) {
        super(selector);
        this.predicate = predicate;
    }

    @Override
    public Predicate<Constructor<T>> predicate() {
        return this.predicate;
    }
}
