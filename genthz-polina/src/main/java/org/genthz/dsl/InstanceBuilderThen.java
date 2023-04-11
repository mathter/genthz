package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;

public interface InstanceBuilderThen {
    default public <T> void ib(InstanceBuilder<T> function) {
        this.instanceBuilder(function);
    }

    public <T> void instanceBuilder(InstanceBuilder<T> function);
}
