package org.genthz.dasha.dsl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class StrictClassSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory contextFactory = new DashaContextFactory();

    @Test
    public void testOrdinal() {
        InstanceContext<S> context = this.contextFactory.single(S.class);
        final Selector selector = new StrictClassSelector(null, S.class);

        Assertions.assertTrue(selector.test(context));
    }

    @Test
    public void testGeneric() {
        InstanceContext<D0> context = this.contextFactory.single(D0.class, String.class);
        Assertions.assertFalse(
                new StrictClassSelector(null,
                        S0.class
                )
                        .test(context)
        );

        Assertions.assertTrue(
                new StrictClassSelector(null,
                        TypeUtils.parameterize(D0.class, String.class)
                )
                        .test(context)
        );
    }
}
