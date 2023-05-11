package org.genthz.dasha.dsl;

import org.genthz.dsl.Metric;
import org.genthz.function.Selector;

import java.util.Optional;

abstract class SelectorOp<THIS extends SelectorOp<THIS>> extends Op<SelectorOp<?>> implements Metric<THIS> {

    private Optional<Integer> metric = Optional.empty();

    public SelectorOp(SelectorOp up) {
        super(up);
    }

    @Override
    public int metric() {
        return this.metric.orElse(0);
    }

    @Override
    public THIS metric(int mertic) {
        this.metric = Optional.of(mertic);
        return (THIS) this;
    }

    public <T extends Metric> T setTo(T metric) {
        this.metric.ifPresent(e -> metric.m(e));

        return metric;
    }

    @Override
    public int compareTo(Metric<THIS> o) {
        throw new UnsupportedOperationException();
    }

    public abstract Selector selector();
}