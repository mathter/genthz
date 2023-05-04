package org.genthz.dasha.dsl;

import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPathSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory factory = new DashaContextFactory();

    @Test
    public void testRoot() {
        final InstanceContext<S> context = this.factory.single(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(null, "/")
                        .test(context)
        );

        Assertions.assertFalse(
                new FixedPathSelector(null, "/")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );

        Assertions.assertTrue(
                new FixedPathSelector(null, "/")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                                        .up()
                        )
        );
    }

    @Test
    public void testSub() {
        final InstanceContext<S> context = this.factory.single(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(null, "stringField")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }

    @Test
    public void testChain() {
        final InstanceContext<S> context = this.factory.single(S.class);

        Assertions.assertTrue(
                new FixedPathSelector(new FixedPathSelector(null, "/"), "stringField")
                        .test(
                                this.factory.byProperties(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }
}
