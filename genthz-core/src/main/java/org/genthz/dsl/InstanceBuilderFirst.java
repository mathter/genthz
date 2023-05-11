package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.Simple;

public interface InstanceBuilderFirst {
    default public <T> FillerThen ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }

    default public <T> FillerThen ib(InstanceBuilderConsumer<T> function) {
        return this.instanceBuilder(function);
    }

    default public <T> FillerThen instanceBuilder(InstanceBuilder<T> function) {
        return this.instanceBuilder(InstanceBuilderConsumer.from(function));
    }

    public <T> FillerThen instanceBuilder(InstanceBuilderConsumer<T> function);

    public <T> void simple();

    public <T> void simple(InstanceBuilderConsumer<T> function);

    default public <T> void simple(InstanceBuilder<T> function) {
        this.simple(InstanceBuilderConsumer.from(function));
    }

    default public <T> void simple(Simple simple) {
        this.simple((InstanceBuilder<T>) simple);
    }
}
