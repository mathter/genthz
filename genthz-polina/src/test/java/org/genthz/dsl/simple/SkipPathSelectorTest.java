package org.genthz.dsl.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.ObjectContext;
import org.genthz.dsl.Selector;
import org.genthz.simple.SimpleContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SkipPathSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory factory = new SimpleContextFactory();

    @Test
    public void test() {
        final ObjectContext<S, ?> context = this.factory.contexts(S.class);
        final Selector selector = new SkipPathSelector(
                new FixedPathSelector(null, "/"),
                1
        );

        Assertions.assertTrue(
                selector.test(
                        this.factory.contexts(context).stream()
                                .filter(e -> "stringField".equals(e.node()))
                                .findFirst()
                                .get()
                )
        );

    }
}
