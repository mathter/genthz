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

import org.genthz.configuration.dsl.Dsl;
import org.genthz.configuration.dsl.DslFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PathSelectorBuilderTest {

    @Test
    public void test() {
        Dsl dsl = DslFactory.dsl();
        Selector selector = PathSelectorBuilder.build(
                dsl.path("/a*/name/../..4/field"),
                null
        );

        Assertions.assertEquals(NameEqualsSelector.class, selector.getClass());
        Assertions.assertEquals(
                SkipSelector.class,
                selector.next()
                        .map(Selector::getClass)
                        .get()
        );
        Assertions.assertEquals(
                NameEqualsSelector.class,
                selector.next()
                        .flatMap(Selector::next)
                        .map(Selector::getClass)
                        .get()
        );
        Assertions.assertEquals(
                MatchedNameSelector.class,
                selector.next()
                        .flatMap(Selector::next)
                        .flatMap(Selector::next)
                        .map(Selector::getClass)
                        .get()
        );
        Assertions.assertEquals(
                RootMatchSelector.class,
                selector.next()
                        .flatMap(Selector::next)
                        .flatMap(Selector::next)
                        .flatMap(Selector::next)
                        .map(Selector::getClass)
                        .get()
        );
        Assertions.assertNull(
                selector.next()
                        .flatMap(Selector::next)
                        .flatMap(Selector::next)
                        .flatMap(Selector::next)
                        .flatMap(Selector::next)
                        .orElse(null)
        );
    }

    @Test
    public void test1() {
        Dsl dsl = DslFactory.dsl();
        Selector selector = PathSelectorBuilder.build(
                dsl.path("field"),
                Optional.of(PathSelectorBuilder.build(dsl.path("a"), null))
        );

        Assertions.assertEquals(
                NameEqualsSelector.class,
                selector.getClass()
        );
        Assertions.assertEquals(
                NameEqualsSelector.class,
                selector.next()
                        .map(Selector::getClass)
                        .get()
        );
        Assertions.assertNull(
                selector.next()
                        .flatMap(Selector::next)
                        .orElse(null)
        );
    }
}
