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
package org.genthz.configuration.dsl;

import org.genthz.ObjectFactory;
import org.genthz.configuration.dsl.model.Deep;
import org.genthz.configuration.dsl.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectGenerationTest {
    @Test
    public void test() {
        final ObjectFactory objectFactory = new AbstractDsl().objectFactory();
        final User user = objectFactory.build(User.class);

        Assertions.assertNotNull(user);
    }

    @Test
    public void testDeep() {
        final Dsl dsl = new AbstractDsl();
        final ObjectFactory objectFactory = dsl.objectFactory();
        final int deepLength = dsl.defaults().defaultDeep().apply(null);
        Deep deep = objectFactory.build(Deep.class);


        for (int i = 0; i < deepLength; i++) {
            deep = deep.getDeep();
        }

        Assertions.assertNull(deep);
    }
}
