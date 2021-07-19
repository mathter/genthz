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

import java.util.function.BiFunction;

public class CollectionFiller<T, C> extends Selectable<T> implements org.genthz.configuration.dsl.CollectionFiller<T, C> {

    private final Class<T> collectionClass;

    private final Class<C> componentClass;

    private BiFunction<Context<T>, T, T> custom;

    private BiFunction<Context<C>, C, C> componentCustom;

    private final int count;

    public CollectionFiller(Selector<T> selector, Class<T> collectionClazz, Class<C> componentClazz, int count) {
        super(selector);
        this.collectionClass = collectionClazz;
        this.componentClass = componentClazz;
        this.count = count;
    }

    @Override
    public Class<T> collectionClass() {
        return null;
    }

    @Override
    public Class<C> componentClass() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public org.genthz.configuration.dsl.CollectionFiller<T, C> custom(BiFunction<Context<T>, T, T> function) {
        return null;
    }

    @Override
    public Filler<T> custom() {
        return null;
    }

    @Override
    public org.genthz.configuration.dsl.CollectionFiller<T, C> componentCustom(BiFunction<Context<C>, C, C> function) {
        return null;
    }

    @Override
    public Filler<C> componentCustom() {
        return null;
    }
}
