package org.genthz.dasha.dsl;

import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.Selector;
import org.genthz.reflection.GenericUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StrictClassSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory contextFactory = new DashaContextFactory();

    @Test
    public void testOrdinal() {
        InstanceContext<?, ?> context = this.contextFactory.context(S.class);
        final Selector selector = new StrictClassSelector(null, S.class);

        Assertions.assertTrue(selector.test(context));
    }

    @Test
    public void testGeneric() {
        InstanceContext<?, ?> context = this.contextFactory.context(D0.class, String.class);
        Assertions.assertFalse(
                new StrictClassSelector(null,
                        S0.class
                )
                        .test(context)
        );

        Assertions.assertTrue(
                new StrictClassSelector(null,
                        GenericUtil.resolve(D0.class, GenericUtil.attribution(D0.class, String.class))
                )
                        .test(context)
        );
    }
}
