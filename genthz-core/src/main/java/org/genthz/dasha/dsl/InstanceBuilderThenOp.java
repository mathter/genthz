package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.Selector;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InstanceBuilderThenOp extends Op<SelectorOp<?>> implements InstanceBuilderThen {
    private final Filler<?> fillerFunction;

    private InstanceBuilderConsumer<?> function;

    public InstanceBuilderThenOp(SelectorOp up, Filler<?> fillerFunction) {
        super(up);
        this.fillerFunction = fillerFunction;
    }

    @Override
    public <T> void instanceBuilder(InstanceBuilderConsumer<T> function) {
        this.function = function;
        this.dsl().reg(this);
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        final Selector selector = this.up().selector();

        return Stream.of(
                Pair.of(selector, this.fillerFunction),
                Pair.of(selector, this.function)
        ).collect(Collectors.toList());
    }
}
