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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

public class DslTest {

    @Test
    public void test() {
        final Configuration configuration = new DefaultConfiguration() {
            {
                name("myconfig");

                reg(
                        path("name").filler((c, v) -> RandomStringUtils.random(5))
                );

                reg(
                        path("middleName").filler((c, v) -> RandomStringUtils.random(5)),
                        path("lastName").filler((c, v) -> RandomStringUtils.random(5))
                );
            }
        }
                .reg(c -> Arrays.asList(
                        c.path("birthDate").strict(Date.class).instance(ctx -> new Date()),
                        c.path("sex").strict(String.class).instance(ctx -> RandomUtils.nextBoolean() ? "M" : "F")
                        )
                )
                .reg(c -> Arrays.asList((c.path("code").strict(String.class).instance(ctx -> "ONE"))));
    }
}
