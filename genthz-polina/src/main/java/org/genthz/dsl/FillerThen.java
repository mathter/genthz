package org.genthz.dsl;

import org.genthz.function.Filler;

public interface FillerThen {
    default public <T> void f(Filler<T> function) {
        this.filler(function);
    }

    public <T> void filler(Filler<T> function);
}
