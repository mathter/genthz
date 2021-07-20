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
package org.genthz.configuration.dsl;

import org.genthz.configuration.dsl.data.Person;
import org.genthz.configuration.dsl.data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

public class PathTest {
    @Test
    public void test0() {
        Person value = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class).use(c ->
                                Arrays.asList(
                                        c.instance(InstanceBuilders.byConstructorArgCount(0)),
                                        c.filler(Fillers.excluding("lastName", "birthday"))
                                )
                        )
                );

                reg(path("name").instance(c -> "Name"));
            }
        }
                .build()
                .factory().build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertEquals("Name", value.getName());
        Assertions.assertNull(value.getLastName());
        Assertions.assertNull(value.getBirthday());
    }

    @Test
    public void test1() {
        Person value = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class).use(c ->
                                Arrays.asList(
                                        c.instance(InstanceBuilders.byConstructorArgCount(0)),
                                        c.filler(Fillers.excluding("lastName", "birthday"))
                                )
                        )
                );

                reg(path("/name").instance(c -> "Name"));
            }
        }
                .build()
                .factory().build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertEquals("Name", value.getName());
        Assertions.assertNull(value.getLastName());
        Assertions.assertNull(value.getBirthday());
    }

    @Test
    public void test2() {
        User value = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class).use(c ->
                                Arrays.asList(
                                        c.instance(InstanceBuilders.byConstructorArgCount(0)),
                                        c.filler(Fillers.excluding("lastName", "birthday"))
                                )
                        )
                );

                reg(path("/person/name").instance(c -> "Name"));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertEquals("Name", value.getPerson().getName());
        Assertions.assertNull(value.getPerson().getLastName());
        Assertions.assertNull(value.getPerson().getBirthday());
    }

    @Test
    public void test3() {
        User value = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class).use(c ->
                                Arrays.asList(
                                        c.instance(InstanceBuilders.byConstructorArgCount(0)),
                                        c.filler(Fillers.excluding("lastName", "birthday"))
                                )
                        )
                );

                reg(path("/../name").instance(c -> "Name"));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertEquals("Name", value.getPerson().getName());
        Assertions.assertNull(value.getPerson().getLastName());
        Assertions.assertNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertNotNull(value.getPerson().getIdCard().getUuid());
    }

    @Test
    public void test4() {
        User value = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class).use(c ->
                                Arrays.asList(
                                        c.instance(InstanceBuilders.byConstructorArgCount(0)),
                                        c.filler(Fillers.excluding("lastName", "birthday"))
                                )
                        )
                );

                reg(path("/../../name").instance(c -> "Name"));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertNotEquals("Name", value.getPerson().getName());
        Assertions.assertNull(value.getPerson().getLastName());
        Assertions.assertNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertNotNull(value.getPerson().getIdCard().getUuid());
    }

    @Test
    public void test5() {
        UUID uuid = UUID.randomUUID();
        User value = new DefaultConfiguration() {
            {
                reg(path("/../../uuid").instance(c -> uuid));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertNotNull(value.getPerson().getName());
        Assertions.assertNotNull(value.getPerson().getLastName());
        Assertions.assertNotNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertEquals(uuid, value.getPerson().getIdCard().getUuid());
    }

    @Test
    public void test6() {
        UUID uuid = UUID.randomUUID();
        User value = new DefaultConfiguration() {
            {
                reg(path("/..2/uuid").instance(c -> uuid));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertNotNull(value.getPerson().getName());
        Assertions.assertNotNull(value.getPerson().getLastName());
        Assertions.assertNotNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertEquals(uuid, value.getPerson().getIdCard().getUuid());
    }

    @Test
    public void test7() {
        UUID uuid = UUID.randomUUID();
        User value = new DefaultConfiguration() {
            {
                reg(path("/..2/uu*").instance(c -> uuid));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertNotNull(value.getPerson().getName());
        Assertions.assertNotNull(value.getPerson().getLastName());
        Assertions.assertNotNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertEquals(uuid, value.getPerson().getIdCard().getUuid());
    }

    @Test
    public void test8() {
        User value = new DefaultConfiguration() {
            {
                reg(path("person/name").instance(c -> "NAME"));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertEquals("NAME", value.getPerson().getName());
        Assertions.assertNotNull(value.getPerson().getLastName());
        Assertions.assertNotNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertNotNull(value.getPerson().getIdCard().getUuid());
        Assertions.assertNotEquals("NAME", value.getPerson().getIdCard().getName());
    }

    @Test
    public void test9() {
        User value = new DefaultConfiguration() {
            {
                reg(path("name").instance(c -> "NAME"));
            }
        }
                .build()
                .factory().build(User.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getLogin());
        Assertions.assertNotNull(value.getPassword());
        Assertions.assertEquals("NAME", value.getPerson().getName());
        Assertions.assertNotNull(value.getPerson().getLastName());
        Assertions.assertNotNull(value.getPerson().getBirthday());
        Assertions.assertNotNull(value.getPerson().getIdCard());
        Assertions.assertNotNull(value.getPerson().getIdCard().getUuid());
        Assertions.assertEquals("NAME", value.getPerson().getIdCard().getName());
    }
}
