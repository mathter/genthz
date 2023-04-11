package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;

public interface InstanceBuilderFirst {
    default public <T> FillerThen ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }

    public <T> FillerThen instanceBuilder(InstanceBuilder<T> function);

    public <T> void simple();

    public <T> void simple(InstanceBuilder<T> function);
}
