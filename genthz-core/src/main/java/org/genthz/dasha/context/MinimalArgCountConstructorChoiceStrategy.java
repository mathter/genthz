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
package org.genthz.dasha.context;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.ConstructorChoiceStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.stream.Stream;

public class MinimalArgCountConstructorChoiceStrategy implements ConstructorChoiceStrategy {
    @Override
    public <T> Constructor<T> constructor(Type type) {
        final Constructor<T> result;
        final Class<T> clazz = (Class<T>) TypeUtils.getRawType(type, Object.class);

        if (clazz.isInterface()) {
            result = null;
        } else {
            result = (Constructor<T>) Stream.of(clazz.getDeclaredConstructors())
                    .min((l, r) -> l.getParameterCount() - r.getParameterCount())
                    .get();
        }

        return result;
    }
}
