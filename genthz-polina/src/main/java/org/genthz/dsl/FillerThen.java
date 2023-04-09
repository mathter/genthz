package org.genthz.dsl;

import org.genthz.function.Filler;

public interface FillerThen {
    default public <T> Selectable f(Filler<T> function) {
        return this.filler(function);
    }

    public <T> Selectable filler(Filler<T> function);
}
