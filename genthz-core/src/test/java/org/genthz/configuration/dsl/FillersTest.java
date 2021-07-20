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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FillersTest {
    @Test
    public void testDef() {
        Person value = new DefaultConfiguration()
                .reg(c -> Arrays.asList(c.strict(Person.class).filler(Fillers.def())))
                .build()
                .factory()
                .build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthday());
    }

    @Test
    public void testIncluding() {
        Person value = new DefaultConfiguration()
                .reg(c -> Arrays.asList(c.strict(Person.class).filler(Fillers.including("name", "birthday"))))
                .build()
                .factory()
                .build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getName());
        Assertions.assertNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthday());
    }

    @Test
    public void testExcluding() {
        Person value = new DefaultConfiguration()
                .reg(c -> Arrays.asList(c.strict(Person.class).filler(Fillers.excluding("lastName"))))
                .build()
                .factory()
                .build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getName());
        Assertions.assertNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthday());
    }
}
