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

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.loly.test.WithRecursion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class RecursionTest {
    @Test
    public void test() {
        Stream
                .of(1L, 5L, 10L, 100L)
                .forEach(deep -> {
                    final ObjectFactory factory = ObjectFactoryProducer
                            .producer()
                            .factory(new DefaultConfiguration() {
                                @Override
                                public Supplier<Long> maxGenerationDeep() {
                                    return () -> deep;
                                }
                            });

                    WithRecursion value = factory.build(WithRecursion.class);

                    for (int i = 0; i < deep; i++, value = value.getNext()) {
                        Assertions.assertNotNull(value);
                        Assertions.assertNotNull(value.getUuid());
                    }

                    Assertions.assertNull(value.getNext());
                });
    }
}
