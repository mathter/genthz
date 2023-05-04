package org.genthz.dasha.dsl;

import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.Filler;

class FillerFirstOp extends Op<SelectorOp> implements FillerFirst {
    public FillerFirstOp(SelectorOp up) {
        super(up);
    }

    @Override
    public <T> InstanceBuilderThen filler(Filler<T> function) {
        this.dsl().reg(this.up().selector(), function);

        return new InstanceBuilderThenOp(this.up());
    }
}
