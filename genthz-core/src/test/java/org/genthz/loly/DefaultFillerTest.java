/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2021 mathter@mail.ru
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

import org.genthz.Context;
import org.genthz.Filler;
import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class DefaultFillerTest {

    @Test
    public void test() {
        ObjectFactory objectFactory = ObjectFactoryProducer
                .producer()
                .factory(new DefaultConfiguration() {
                    {
                        reg(
                                strict(A.class).defaultFiller()
                        );
                    }
                });

        A value = objectFactory.build(A.class);

        Assertions.assertNotNull(value.uuid);
        Assertions.assertNotNull(value.name);
        Assertions.assertNotNull(value.lastName);
        Assertions.assertNotNull(value.birthDate);
    }

    @Test
    public void testIncluding() {
        ObjectFactory objectFactory = ObjectFactoryProducer
                .producer()
                .factory(new DefaultConfiguration() {
                    {
                        reg(
                                strict(A.class)
                                        .defaultFiller().including("name")
                        );
                    }
                });

        A value = objectFactory.build(A.class);

        Assertions.assertNull(value.uuid);
        Assertions.assertNotNull(value.name);
        Assertions.assertNull(value.lastName);
        Assertions.assertNull(value.birthDate);
    }

    @Test
    public void testCustom() {
        ObjectFactory objectFactory = ObjectFactoryProducer
                .producer()
                .factory(new DefaultConfiguration() {
                    {
                        reg(
                                strict(A.class)
                                        .defaultFiller()
                                        .excluding("name")
                                        .custom(new Filler<A>() {
                                            @Override
                                            public A apply(Context<A> context, A a) {
                                                a.name = "John";
                                                return a;
                                            }
                                        })
                        );
                    }
                });

        A value = objectFactory.build(A.class);

        Assertions.assertNotNull(value.uuid);
        Assertions.assertEquals("John", value.name);
        Assertions.assertNotNull(value.lastName);
        Assertions.assertNotNull(value.birthDate);
    }

    @Test
    public void testExcluding() {
        ObjectFactory objectFactory = ObjectFactoryProducer
                .producer()
                .factory(new DefaultConfiguration() {
                    {
                        reg(
                                strict(A.class)
                                        .defaultFiller().excluding("name")
                        );
                    }
                });

        A value = objectFactory.build(A.class);

        Assertions.assertNotNull(value.uuid);
        Assertions.assertNull(value.name);
        Assertions.assertNotNull(value.lastName);
        Assertions.assertNotNull(value.birthDate);
    }

    @Test
    public void testStaticAndFina() {
        ObjectFactory objectFactory = ObjectFactoryProducer.producer().factory(new DefaultConfiguration());

        B value = objectFactory.build(B.class);

        Assertions.assertNotNull(value);
        Assertions.assertEquals("S0", value.S0);
        Assertions.assertEquals("f0", value.f0);
        Assertions.assertNotNull(value.f1);
    }

    private static class A {
        private UUID uuid;

        private String name;

        private String lastName;

        private Date birthDate;
    }

    private static class B {
        private static String S0 = "S0";

        private final String f0 = "f0";

        private String f1;
    }
}
