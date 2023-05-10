package org.genthz.dasha.dsl;

import org.genthz.dsl.Metric;
import org.genthz.function.Selector;

abstract class SelectorOp extends Op<SelectorOp> implements Metric<SelectorOp> {

    private int metric;

    public SelectorOp(SelectorOp up) {
        super(up);
    }

    @Override
    public int metric() {
        return this.metric;
    }

    @Override
    public SelectorOp metric(int mertic) {
        this.metric = mertic;
        return this;
    }

    @Override
    public int compareTo(Metric<SelectorOp> o) {
        return this.metric - o.metric();
    }

    public abstract Selector selector();
}