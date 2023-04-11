package org.genthz.dasha.dsl;

import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.Filler;

class FillerFirstOp implements FillerFirst {
    @Override
    public <T> InstanceBuilderThen filler(Filler<T> function) {
        return new InstanceBuilderThenOp();
    }
}
