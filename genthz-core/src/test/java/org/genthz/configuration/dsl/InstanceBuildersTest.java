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
import org.genthz.configuration.dsl.data.Person0;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class InstanceBuildersTest {

    @Test
    public void testByConstructorOnly() {
        Person value = new DefaultConfiguration()
                .reg(c -> {
                    try {
                        return Arrays.asList(
                                c.strict(Person.class)
                                        .instance(InstanceBuilders.byConstructor(Person.class.getConstructor()))
                        );
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .build()
                .factory()
                .build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNull(value.getName());
        Assertions.assertNull(value.getLastName());
        Assertions.assertNull(value.getBirthday());
    }

    @Test
    public void testByConstructor() {
        Person value = new DefaultConfiguration()
                .reg(c ->
                        c.strict(Person.class)
                                .use(s -> {
                                            try {
                                                return Arrays.asList(
                                                        s.instance(InstanceBuilders.byConstructor(Person.class.getConstructor())),
                                                        s.filler(Fillers.def())
                                                );
                                            } catch (NoSuchMethodException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                )
                )
                .build()
                .factory()
                .build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthday());
    }

    @Test
    public void testByConstructorArgCount() {
        Person value = new DefaultConfiguration()
                .reg(c -> Arrays.asList(c.strict(Person0.class).instance(InstanceBuilders.byConstructorArgCount(2))))
                .build()
                .factory()
                .build(Person0.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNull(value.getBirthday());
    }

    @Test
    public void testByArgumentTypes() {
        Person value = new DefaultConfiguration()
                .reg(c -> Arrays.asList(
                        c.strict(Person0.class).instance(InstanceBuilders.byArgumentTypes(String.class, String.class))
                        )
                )
                .build()
                .factory()
                .build(Person0.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNull(value.getBirthday());
    }
}
