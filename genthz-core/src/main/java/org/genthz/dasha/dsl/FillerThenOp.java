package org.genthz.dasha.dsl;

import org.genthz.dsl.FillerThen;
import org.genthz.function.Filler;

class FillerThenOp extends Op<SelectorOp> implements FillerThen {
    public FillerThenOp(SelectorOp up) {
        super(up);
    }

    @Override
    public <T> void filler(Filler<T> function) {
        this.dsl().reg(this.up().selector(), function);
    }
}
