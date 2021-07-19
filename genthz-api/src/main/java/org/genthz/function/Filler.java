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
package org.genthz.function;

import org.genthz.context.Context;

import java.util.function.BiFunction;

/**
 * Interface represents filler of the object of type <code>T</code>.
 *
 * @param <T> type of object to be filled.
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@FunctionalInterface
public interface Filler<T> extends BiFunction<Context<T>, T, T> {
    public static final Filler UNIT = new Unit();

    /**
     * Unit filler class. This filler do not any work. This filler returns object as it.
     *
     * @param <T> type of the object to be filled by this filler.
     */
    public static class Unit<T> implements Filler<T> {
        @Override
        public T apply(Context<T> context, T value) {
            return value;
        }
    }
}
