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
import org.genthz.context.NodeInstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class SimpleContextFactoryTest {
    private ContextFactory factory = new DashaContextFactory();

    @Test
    public void test() {
        final InstanceContext<C0> objectContext = this.factory.single(C0.class);
        Assertions.assertNotNull(objectContext);

        final Collection<NodeInstanceContext<?, String>> contexts = this.factory.byProperties(objectContext);
        Assertions.assertNotNull(contexts);
        Assertions.assertEquals(1, contexts.size());
    }

    @Test
    public void testGeneric() {
        final InstanceContext<C1> objectContext = this.factory.single(C1.class, String.class, Double.class);
        Assertions.assertNotNull(objectContext);

        final Collection<NodeInstanceContext<?, String>> fieldContexts = this.factory.byProperties(objectContext);
        Assertions.assertNotNull(fieldContexts);
        Assertions.assertEquals(2, fieldContexts.size());
    }

    public static class C0 {
        private String field;
    }

    public static class C1<X, Y> {
        private X x;

        private List<Y> y;
    }
}
