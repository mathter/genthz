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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.configuration.dsl.DslFactory;

public class MaximumGenerationDeepTest {
    private static ObjectFactory OBJECT_FACTORY;

    @BeforeAll
    private static void init() {
        OBJECT_FACTORY = ObjectFactoryProducer
                .producer()
                .factory(
                        new DefaultConfiguration(
                                DslFactory.dsl()
                        )
                );
    }

    @Test
    public void testOneLevel() {
        A value = OBJECT_FACTORY.build(A.class);

        Assertions.assertNotNull(value);
    }

    public static class A {
        private A a;
    }
}
