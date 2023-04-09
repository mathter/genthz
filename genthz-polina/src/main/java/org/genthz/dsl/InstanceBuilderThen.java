package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;

public interface InstanceBuilderThen {
    default public <T> Selectable ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }

    public <T> Selectable instanceBuilder(InstanceBuilder<T> function);
}
