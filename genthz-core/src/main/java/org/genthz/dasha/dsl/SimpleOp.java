package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.Selector;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SimpleOp extends Op<SelectorOp<?>> {
    private final InstanceBuilderConsumer<?> instanceBuilderConsumerFunction;

    private final Filler<?> fillerFunction;

    public SimpleOp(SelectorOp<?> up, InstanceBuilderConsumer<?> instanceBuilderConsumerFunction, Filler<?> fillerFunction) {
        super(up);
        this.instanceBuilderConsumerFunction = instanceBuilderConsumerFunction;
        this.fillerFunction = fillerFunction;
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        final Selector selector = this.up().selector();

        return Stream.of(
                Pair.of(selector, this.instanceBuilderConsumerFunction),
                Pair.of(selector, this.fillerFunction)
        ).collect(Collectors.toList());
    }
}
