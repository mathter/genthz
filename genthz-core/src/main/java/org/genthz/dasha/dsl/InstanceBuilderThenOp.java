/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.dasha.dsl;

import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

class InstanceBuilderThenOp<T> extends InternalFunctionsOp<T> implements InstanceBuilderThen<T> {
    public InstanceBuilderThenOp(SelectorOp<?> up, Filler<T> filler) {
        super(up, filler);
    }

    @Override
    public void instanceBuilder(InstanceBuilder<T> function) {
        this.setInstanceBuilder(function);
    }
}
