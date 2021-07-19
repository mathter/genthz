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
import org.genthz.function.InstanceBuilder;
import org.genthz.configuration.dsl.Selector;

import java.util.function.Function;

class FunctionalInstanceBuilder<T>
        extends Selectable<T>
        implements org.genthz.configuration.dsl.FunctionalInstanceBuilder<T>, InstanceBuilder<T> {
    private final Function<Context<T>, T> function;

    public FunctionalInstanceBuilder(Selector<T> selector, Function<Context<T>, T> function) {
        super(selector);
        this.function = function;
    }

    @Override
    public Function<Context<T>, T> function() {
        return this.function;
    }

    @Override
    public T apply(Context<T> context) {
        return this.function.apply(context);
    }
}
