package org.genthz.dsl;

import org.genthz.function.Filler;

public interface FillerFirst {
    default public <T> InstanceBuilderThen f(Filler<T> function) {
        return this.filler(function);
    }

    public <T> InstanceBuilderThen filler(Filler<T> function);
}
