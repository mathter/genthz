package org.genthz.dsl.simple;

import org.genthz.context.ContextFactory;
import org.genthz.context.ObjectContext;
import org.genthz.simple1.SimpleContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PatternPathSelectorTest extends AbstractClassSelectorTest {
    private ContextFactory factory = new SimpleContextFactory();

    @ParameterizedTest
    @MethodSource("data")
    public void testSub(String pattern) {
        final ObjectContext<S, ?> context = this.factory.contexts(S.class);

        Assertions.assertTrue(
                new PatternPathSelector(null, pattern)
                        .test(
                                this.factory.contexts(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }

    private static Stream<String> data() {
        return Stream.of(
                "stringField",
                "^s.*",
                "s.ringField"
        );
    }

    @Test
    public void testChain() {
        final ObjectContext<S, ?> context = this.factory.contexts(S.class);

        Assertions.assertTrue(
                new PatternPathSelector(new FixedPathSelector(null, "/"), "stringField")
                        .test(
                                this.factory.contexts(context).stream()
                                        .filter(e -> "stringField".equals(e.node()))
                                        .findFirst()
                                        .get()
                        )
        );
    }
}
