package org.genthz.dsl;

import org.genthz.function.Filler;

public interface FillerThen<T> {
    default public void f(Filler<T> function) {
        this.filler(function);
    }

    public void filler(Filler<T> function);
}
