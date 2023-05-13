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

import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPathSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory factory = new DashaContextFactory();

    @Test
    public void testRoot() {
        final InstanceContext<S> context = this.factory.single(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(null, "/")
                        .test(context)
        );

        Assertions.assertFalse(
                new FixedPathSelector(null, "/")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );

        Assertions.assertTrue(
                new FixedPathSelector(null, "/")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                                        .up()
                        )
        );
    }

    @Test
    public void testSub() {
        final InstanceContext<S> context = this.factory.single(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(null, "stringField")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }

    @Test
    public void testChain() {
        final InstanceContext<S> context = this.factory.single(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(new FixedPathSelector(null, "/"), "stringField")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }
}
