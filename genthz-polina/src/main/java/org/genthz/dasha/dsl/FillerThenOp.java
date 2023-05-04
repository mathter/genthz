package org.genthz.dasha.dsl;

import org.genthz.dsl.FillerThen;
import org.genthz.function.Filler;

class FillerThenOp implements FillerThen {
    public FillerThenOp(SelectorOp up) {
    }

    @Override
    public <T> void filler(Filler<T> function) {
        throw new UnsupportedOperationException();
    }
}
