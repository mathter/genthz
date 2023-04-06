package org.genthz.dsl.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.ObjectContext;
import org.genthz.dsl.Selector;
import org.genthz.reflection.GenericUtil;
import org.genthz.simple1.SimpleContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnstrictClassSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory contextFactory = new SimpleContextFactory();

    @Test
    public void testOrdinal() {
        ObjectContext<?, ?> context = this.contextFactory.contexts(S.class);

        final Selector selector = new UnstrictClassSelector(null, S.class);

        Assertions.assertTrue(selector.test(context));
    }

    @Test
    public void testGeneric() {
        ObjectContext<?, ?> context = this.contextFactory.contexts(D0.class, String.class);

        Assertions.assertFalse(
                new UnstrictClassSelector(null,
                        D.class
                )
                        .test(context)
        );

        Assertions.assertTrue(
                new UnstrictClassSelector(null,
                        GenericUtil.resolve(D0.class, GenericUtil.attribution(D0.class, String.class))
                )
                        .test(context)
        );
    }
}
