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
import org.genthz.configuration.dsl.model.Back;
import org.genthz.configuration.dsl.model.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FillersTest {
    @Test
    public void testBack() {
        final ObjectFactory objectFactory = new DefaultDsl() {
            {
                strict(Back.class).path("root").strict(Root.class).metrics(c -> 2).f(Fillers.back());
            }
        }.objectFactory();
        final Root root = objectFactory.build(Root.class);

        Assertions.assertNotNull(root);
        Assertions.assertNotNull(root.getBack());
        Assertions.assertEquals(root, root.getBack().getRoot());
    }

    @Test
    public void testBackNumberSteps() {
        final ObjectFactory objectFactory = new DefaultDsl() {
            {
                strict(Back.class).path("root").strict(Root.class).metrics(c -> 2).f(Fillers.back(1));
            }
        }.objectFactory();
        final Root root = objectFactory.build(Root.class);

        Assertions.assertNotNull(root);
        Assertions.assertNotNull(root.getBack());
        Assertions.assertEquals(root, root.getBack().getRoot());
    }
}
