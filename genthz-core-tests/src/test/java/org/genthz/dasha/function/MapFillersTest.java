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

import org.apache.commons.lang3.RandomUtils;
import org.genthz.ObjectFactory;
import org.genthz.dasha.dsl.DashaDsl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapFillersTest {
    @Test
    public void test() {
        final int size = RandomUtils.nextInt(10, 100);
        final ObjectFactory objectFactory = new DashaDsl() {
            {
                this.def();
                this.strict(Map.class, String.class, Integer.class)
                        .filler(MapFillers.size(size));
            }
        }.objectFactory();
        final Map<String, Integer> instance = objectFactory.get(Map.class, String.class, Integer.class);

        Assertions.assertNotNull(instance);
        Assertions.assertEquals(size, instance.size());
    }
}
