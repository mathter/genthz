package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

import java.util.ArrayList;
import java.util.Collection;

class InternalFunctionsOp<T> extends Op<SelectorOp<?>> {
    private InstanceBuilder<T> instanceBuilder;

    private Filler<T> filler;

    InternalFunctionsOp(SelectorOp<?> up, InstanceBuilder<T> instanceBuilder) {
        this(up, instanceBuilder, null);
    }

    InternalFunctionsOp(SelectorOp<?> up, Filler<T> filler) {
        this(up, null, filler);
    }

    InternalFunctionsOp(SelectorOp<?> up, InstanceBuilder<T> instanceBuilder, Filler<T> filler) {
        super(up);
        this.instanceBuilder = instanceBuilder;
        this.filler = filler;
        this.dsl().reg(this);
    }

    public void setInstanceBuilder(InstanceBuilder<T> instanceBuilder) {
        this.instanceBuilder = instanceBuilder;
    }

    public void setFiller(Filler<T> filler) {
        this.filler = filler;
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        final Collection<Pair<Selector, ?>> result = new ArrayList<>(2);
        final Selector selector = this.up().selector();

        if (this.instanceBuilder != null) {
            result.add(Pair.of(selector, this.instanceBuilder));
        }

        if (this.filler != null) {
            result.add(Pair.of(selector, this.filler));
        }

        return result;
    }
}
