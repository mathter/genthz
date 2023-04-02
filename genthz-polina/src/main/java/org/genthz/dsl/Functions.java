package org.genthz.dsl;

import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

public interface Functions {
    default public <T> Selectable ib(InstanceBuilder<T> function) {
        return this.instanceBuilder(function);
    }

    public <T> Selectable instanceBuilder(InstanceBuilder<T> function);

    public <T> Selectable simple();

    public <T> Selectable simple(InstanceBuilder<T> function);

    default public <T> Selectable f(Filler<T> function) {
        return this.filler(function);
    }

    public <T> Selectable filler(Filler<T> function);
}
