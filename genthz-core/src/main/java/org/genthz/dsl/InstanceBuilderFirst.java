package org.genthz.dsl;

import org.genthz.function.InstanceBuilder;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.Tail;

public interface InstanceBuilderFirst<T> {
    default public FillerThen ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }

    default public FillerThen ib(InstanceBuilderConsumer<T> function) {
        return this.instanceBuilder(function);
    }

    default public FillerThen instanceBuilder(InstanceBuilder<T> function) {
        return this.instanceBuilder(InstanceBuilderConsumer.from(function));
    }

    public FillerThen instanceBuilder(InstanceBuilderConsumer<T> function);

    public void simple();

    public void simple(InstanceBuilderConsumer<T> function);

    default public void simple(InstanceBuilder<T> function) {
        this.simple(InstanceBuilderConsumer.from(function));
    }

    default public void tail(Tail simple) {
        this.simple((InstanceBuilder<T>) simple);
    }
}
