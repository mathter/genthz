package org.genthz.dsl;

import org.genthz.function.Filler;

public interface FillerFirst {
    default public <T, S extends Selectable & InstanceBuilderThen> S f(Filler<T> function) {
        return this.filler(function);
    }

    public <T, S extends Selectable & InstanceBuilderThen> S filler(Filler<T> function);
}
