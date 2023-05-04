package org.genthz.dasha.dsl;

import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SkipPathSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory factory = new DashaContextFactory();

    @Test
    public void test() {
        final InstanceContext<S> context = this.factory.single(S.class);
        final Selector selector = new SkipPathSelector(
                new FixedPathSelector(null, "/"),
                1
        );

        Assertions.assertTrue(
                selector.test(
                        this.factory.byProperties(context).stream()
                                .filter(e -> "stringField".equals(e.node()))
                                .findFirst()
                                .get()
                )
        );

    }
}
