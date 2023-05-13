package org.genthz.dsl;

import org.genthz.function.Filler;

public interface FillerFirst<T> {
    default public InstanceBuilderThen<T> f(Filler<T> function) {
        return this.filler(function);
    }

    public InstanceBuilderThen<T> filler(Filler<T> function);
}
