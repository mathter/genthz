package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;

public interface InstanceBuilderFirst {
    default public <T, S extends Selectable & FillerThen> S ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }

    public <T, S extends Selectable & FillerThen> S instanceBuilder(InstanceBuilder<T> function);

    public <T> Selectable simple();

    public <T> Selectable simple(InstanceBuilder<T> function);
}
