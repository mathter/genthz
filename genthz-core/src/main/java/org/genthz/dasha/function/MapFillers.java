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
package org.genthz.dasha.function;

import org.genthz.function.DefaultMapFiller;
import org.genthz.function.Filler;

import java.util.Map;

/**
 * Predefined fillers for {@linkplain Map}.
 *
 * @since 3.0.3
 */
public abstract class MapFillers {
    /**
     * Method creates a filler for a map having selected count of an elements.
     *
     * @param size count of collection elements.
     * @param <T>  type of map.
     * @return filler.
     * @since 3.0.3
     */
    public static <T extends Map> Filler<T> size(int size) {
        return new DefaultMapFiller(context -> size);
    }

    private MapFillers() {
    }
}
