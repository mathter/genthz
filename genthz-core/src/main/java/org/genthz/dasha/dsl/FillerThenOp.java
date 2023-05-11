package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.dsl.FillerThen;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FillerThenOp extends Op<SelectorOp<?>> implements FillerThen {
    private final InstanceBuilder<?> instanceBuilderFunction;

    private Filler<?> function;

    public FillerThenOp(SelectorOp up, InstanceBuilder<?> instanceBuilderFunction) {
        super(up);
        this.instanceBuilderFunction = instanceBuilderFunction;
    }

    @Override
    public <T> void filler(Filler<T> function) {
        this.function = function;
        this.dsl().reg(this);
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        final Selector selector = this.up().selector();

        return Stream.of(
                Pair.of(selector, this.instanceBuilderFunction),
                Pair.of(selector, this.function)
        ).collect(Collectors.toList());
    }
}