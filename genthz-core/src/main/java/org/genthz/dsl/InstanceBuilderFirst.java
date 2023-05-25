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
package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;
import org.genthz.function.Tail;

public interface InstanceBuilderFirst<T> {
    default public FillerThen<T> ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }


    public FillerThen<T> instanceBuilder(InstanceBuilder<T> function);

    public void simple();

    public void simple(InstanceBuilder<T> function);

    default public void tail(Tail<T> tail) {
        this.simple(tail);
    }
}
