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

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StrictClassSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory contextFactory = new DashaContextFactory();

    @Test
    public void testOrdinal() {
        InstanceContext<S> context = this.contextFactory.single(S.class);
        final Selector selector = new StrictClassSelector(null, S.class);

        Assertions.assertTrue(selector.test(context));
    }

    @Test
    public void testGeneric() {
        InstanceContext<D0> context = this.contextFactory.single(D0.class, String.class);
        Assertions.assertFalse(
                new StrictClassSelector(null,
                        S0.class
                )
                        .test(context)
        );

        Assertions.assertTrue(
                new StrictClassSelector(null,
                        TypeUtils.parameterize(D0.class, String.class)
                )
                        .test(context)
        );
    }
}
