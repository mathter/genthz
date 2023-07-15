/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.dasha.dsl;

import org.genthz.function.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class Antrl4PathProcessorTest {
    @Test
    public void testRoot() {
        final Selector selector = Antrl4PathProcessor.path(null, "/");

        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertFalse(selector.up().isPresent());
        Assertions.assertEquals("/", ((FixedPathSelector) selector).getElement());
    }

    @Test
    public void testSomeElement() {
        final Selector selector = Antrl4PathProcessor.path(null, "field");

        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertFalse(selector.up().isPresent());
        Assertions.assertEquals("field", ((FixedPathSelector) selector).getElement());
    }

    @Test
    public void testMatchedElement() {
        final Selector selector = Antrl4PathProcessor.path(null, "f*d");

        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof PatternPathSelector);
        Assertions.assertFalse(selector.up().isPresent());
        Assertions.assertEquals(Pattern.compile("f.*d").pattern(), ((PatternPathSelector) selector).getPattern().pattern());
    }

    @Test
    public void testChain() {
        Selector selector = Antrl4PathProcessor.path(null, "/f0/f1/f*d");
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof PatternPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals(Pattern.compile("f.*d").pattern(), ((PatternPathSelector) selector).getPattern().pattern());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals("f1", ((FixedPathSelector) selector).getElement());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals("f0", ((FixedPathSelector) selector).getElement());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertFalse(selector.up().isPresent());
        Assertions.assertEquals("/", ((FixedPathSelector) selector).getElement());
    }

    @Test
    public void testSkip() {
        Selector selector = Antrl4PathProcessor.path(null, "/f/..2/..4/s");
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals("s", ((FixedPathSelector) selector).getElement());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof SkipPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals(4, ((SkipPathSelector) selector).getSkip());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof SkipPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals(2, ((SkipPathSelector) selector).getSkip());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertTrue(selector.up().isPresent());
        Assertions.assertEquals("f", ((FixedPathSelector) selector).getElement());

        selector = selector.up().get();
        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selector instanceof FixedPathSelector);
        Assertions.assertFalse(selector.up().isPresent());
        Assertions.assertEquals("/", ((FixedPathSelector) selector).getElement());
    }
}
