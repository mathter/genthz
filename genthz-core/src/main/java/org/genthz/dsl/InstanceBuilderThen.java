package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;
import org.genthz.function.InstanceBuilderConsumer;

public interface InstanceBuilderThen {
    default public <T> void ib(InstanceBuilder<T> function) {
        this.instanceBuilder(function);
    }

    default public <T> void ib(InstanceBuilderConsumer<T> function) {
        this.instanceBuilder(function);
    }

    default public <T> void instanceBuilder(InstanceBuilder<T> functon) {
        this.instanceBuilder(InstanceBuilderConsumer.from(functon));
    }

    public <T> void instanceBuilder(InstanceBuilderConsumer<T> function);
}
