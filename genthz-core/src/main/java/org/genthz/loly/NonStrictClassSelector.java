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
package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Description;
import org.genthz.Util;

import java.util.Optional;
import java.util.function.Function;

class NonStrictClassSelector<T, B> extends ClassSelector<T, NonStrictClassSelector<B, T>> {

    public NonStrictClassSelector(
            String name,
            Function<Context<?>, Long> metrics,
            Optional<Selector> next,
            Class<T> clazz,
            Description description
    ) {
        super(
                name,
                metrics,
                next,
                (context) -> {
                    return clazz.isAssignableFrom(context.clazz());
                },
                () -> new NonStrictClassSelector(name + ".boxed", metrics, next, Util.getDual(clazz), description),
                description
        );
    }
}
