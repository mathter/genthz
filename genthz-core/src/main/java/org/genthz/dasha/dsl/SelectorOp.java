package org.genthz.dasha.dsl;

import org.genthz.dsl.Metric;
import org.genthz.function.Selector;

abstract class SelectorOp<THIS extends SelectorOp<THIS>> extends Op<SelectorOp<?>> implements Metric<THIS> {

    private int metric;

    public SelectorOp(SelectorOp up) {
        super(up);
    }

    @Override
    public int metric() {
        return this.metric;
    }

    @Override
    public THIS metric(int mertic) {
        this.metric = mertic;
        return (THIS) this;
    }

    @Override
    public int compareTo(Metric<THIS> o) {
        return this.metric - o.metric();
    }

    public abstract Selector selector();
}