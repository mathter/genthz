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

import org.genthz.configuration.Filler;
import org.genthz.configuration.InstanceBuilder;
import org.genthz.configuration.dsl.Selector;
import org.genthz.context.context.Context;

class InstanceBuilderSelectable<T> extends AbstractSelectable<T> implements
        org.genthz.configuration.dsl.InstanceBuilderSelectable<T>,
        InstanceBuilder<T> {
    private final InstanceBuilder<T> function;

    private boolean isSimple = true;

    public InstanceBuilderSelectable(Selector<T> selector, InstanceBuilder<T> function) {
        super(selector);
        this.function = function;
    }

    @Override
    public T apply(Context<T> context) {
        return this.function.apply(context);
    }

    @Override
    public InstanceBuilderSelectable<T> ib(InstanceBuilder<T> instanceBuilder) {
        throw new IllegalStateException();
    }

    @Override
    public FillerSelectable<T> f(Filler<T> filler) {
        final FillerSelectable<T> fillerSelectable = super.f(filler);
        this.isSimple = false;

        return fillerSelectable;
    }

    public InstanceBuilder<T> getFunction() {
        return function;
    }

    public boolean isSimple() {
        return isSimple;
    }

    public void setSimple(boolean simple) {
        isSimple = simple;
    }
}
