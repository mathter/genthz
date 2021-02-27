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

abstract class Selectable<T> implements org.genthz.configuration.dsl.Selectable {

    private final Selector selector;

    private boolean isSimple = false;

    protected Selectable(Selector selector) {
        this.selector = selector;
    }

    @Override
    public String name() {
        return this.selector.name();
    }

    @Override
    public Selector selector() {
        return this.selector;
    }

    @Override
    public void simple() {
        this.isSimple = true;
    }

    @Override
    public boolean isSimple() {
        return this.isSimple;
    }
}
