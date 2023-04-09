package org.genthz.dasha.dsl;

import org.genthz.dasha.dsl.Antrl4PathProcessor;
import org.genthz.dasha.dsl.FixedPathSelector;
import org.genthz.dasha.dsl.PatternPathSelector;
import org.genthz.dsl.Selector;
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
}
