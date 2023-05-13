package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;
import org.genthz.function.InstanceBuilderConsumer;

public interface InstanceBuilderThen<T> {
    default public void ib(InstanceBuilder<T> function) {
        this.instanceBuilder(function);
    }

    default public void ib(InstanceBuilderConsumer<T> function) {
        this.instanceBuilder(function);
    }

    default public void instanceBuilder(InstanceBuilder<T> functon) {
        this.instanceBuilder(InstanceBuilderConsumer.from(functon));
    }

    public void instanceBuilder(InstanceBuilderConsumer<T> function);
}
