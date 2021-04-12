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

import org.apache.commons.lang3.RandomStringUtils;
import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.loly.test.Employee;
import org.genthz.loly.test.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

public class ComplexTest {
    private static String MANAGER_NAME_0;

    private static String MANAGER_NAME_1;

    private static Configuration CONF;

    @BeforeAll
    public static void init() {
        MANAGER_NAME_0 = RandomStringUtils.randomAlphanumeric(10);
        MANAGER_NAME_1 = RandomStringUtils.randomAlphanumeric(10);
        CONF = new DefaultConfiguration() {
            {
                reg(
                        not(strict(Manager.class).path(".."))
                                .strict(Employee.class)
                                .path("manager")
                                .strict(Manager.class)
                                .defaultFiller().excluding("employees")
                                .<Manager>custom((context, manager) -> {
                                            manager.setName(MANAGER_NAME_0);
                                            manager.setEmployees(Collections.singletonList((Employee) context.parentNode().get()));
                                            return manager;
                                        }
                                )
                );

                reg(
                        not(strict(Employee.class).path(".."))
                                .strict(Manager.class)
                                .use(
                                        (a, s) -> {
                                            a.add(s.defaultFiller().<Manager>custom((context, manager) -> {
                                                        manager.setName(MANAGER_NAME_1);
                                                        return manager;
                                                    }
                                            ));
                                            a.add(
                                                    s.path("employees")
                                                            .nonstrict(Collection.class)
                                                            .collectionFiller(Employee.class, 5)
                                                            .componentCustom((context, employee) -> {
                                                                employee.setManager((Manager) context.parentNode().get());
                                                                return employee;
                                                            })
                                            );
                                        }
                                )
                );
            }
        };
    }

    @Test
    public void testEmployee() {
        final ObjectFactory factory = ObjectFactoryProducer
                .producer()
                .factory(CONF);

        Employee value = factory.build(Employee.class);
        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthDate());

        Assertions.assertNotNull(value.getManager());
        Assertions.assertNotNull(value.getManager().getUuid());
        Assertions.assertNotNull(value.getManager().getName());
        Assertions.assertEquals(MANAGER_NAME_0, value.getManager().getName());
        Assertions.assertNotNull(value.getManager().getLastName());
        Assertions.assertNotNull(value.getManager().getBirthDate());
        Assertions.assertNotNull(value.getManager().getEmployees());
        Assertions.assertEquals(1, value.getManager().getEmployees().size());
    }

    @Test
    public void testManager() {
        final ObjectFactory factory = ObjectFactoryProducer
                .producer()
                .factory(CONF);

        Manager value = factory.build(Manager.class);
        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getName());
        Assertions.assertEquals(MANAGER_NAME_1, value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthDate());
    }

    @Test
    public void testBigInteger() {
        final ObjectFactory factory = ObjectFactoryProducer
                .producer()
                .factory(new DefaultConfiguration());

        Assertions.assertNotNull(factory.build(A.class).n);
    }

    private static class A {
        private BigInteger n;
    }
}
