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

import org.apache.commons.lang3.RandomStringUtils;
import org.genthz.configuration.dsl.Dsl;
import org.genthz.configuration.dsl.DslFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathSelectorBuilderTest {

    @Test
    public void test() {
        Dsl dsl = DslFactory.dsl();
        Selector selector = PathSelectorBuilder.build(
                dsl
                        .path("/a*/name/../..4/das")
                        .nonstrict((c) -> RandomStringUtils.randomAlphanumeric(10), String.class)
                        .selector()
                        .next(),
                null
        );

        Assertions.assertEquals(NameEqualsSelector.class, selector.getClass());
        Assertions.assertEquals(SkipSelector.class, selector.next().getClass());
        Assertions.assertEquals(NameEqualsSelector.class, selector.next().next().getClass());
        Assertions.assertEquals(MatchedNameSelector.class, selector.next().next().next().getClass());
        Assertions.assertEquals(RootMatchSelector.class, selector.next().next().next().next().getClass());
        Assertions.assertNull(selector.next().next().next().next().next());
    }
}
