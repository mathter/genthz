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

import org.genthz.configuration.dsl.Selector;
import org.genthz.context.context.Context;

class ClassUnstrictSelector<T> extends ClassSelector<T> {
    public ClassUnstrictSelector(Selector<?> prev, Class<T> clazz) {
        super(prev, clazz);
    }

    @Override
    public boolean test(Context<?> context) {
        return context != null && this.clazz.isAssignableFrom(context.clazz()) && super.test(context);
    }
}