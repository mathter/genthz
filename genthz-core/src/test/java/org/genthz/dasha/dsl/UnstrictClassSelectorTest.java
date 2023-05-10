package org.genthz.dasha.dsl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.dasha.context.DashaContextFactory;
import org.genthz.function.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class UnstrictClassSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory contextFactory = new DashaContextFactory();

    @Test
    public void testOrdinal() {
        InstanceContext<?> context = this.contextFactory.single(S.class);

        final Selector selector = new UnstrictClassSelector(null, S.class);

        Assertions.assertTrue(selector.test(context));
    }

    @Test
    public void testGeneric() {
        InstanceContext<?> context = this.contextFactory.single(D0.class, String.class);

        Assertions.assertTrue(
                new UnstrictClassSelector(null,
                        D.class
                )
                        .test(context)
        );

        Assertions.assertTrue(
                new UnstrictClassSelector(null,
                        TypeUtils.parameterize(D.class, String.class)
                )
                        .test(context)
        );
    }
}
