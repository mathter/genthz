package org.genthz.dsl.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.ObjectContext;
import org.genthz.simple.SimpleContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPathSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory factory = new SimpleContextFactory();

    @Test
    public void testRoot() {
        final ObjectContext<S, ?> context = this.factory.contexts(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(null, "/")
                        .test(context)
        );

        Assertions.assertFalse(
                new FixedPathSelector(null, "/")
                        .test(
                                this.factory.contexts(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );

        Assertions.assertTrue(
                new FixedPathSelector(null, "/")
                        .test(
                                this.factory.contexts(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                                        .parent()
                        )
        );
    }

    @Test
    public void testSub() {
        final ObjectContext<S, ?> context = this.factory.contexts(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(null, "stringField")
                        .test(
                                this.factory.contexts(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }

    @Test
    public void testChain() {
        final ObjectContext<S, ?> context = this.factory.contexts(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(new FixedPathSelector(null, "/"), "stringField")
                        .test(
                                this.factory.contexts(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }
}
