package org.genthz.configuration.dsl;

import org.genthz.InstanceBuilder;

public interface FunctionalInstanceBuilder<T> extends Selectable {
    /**
     * Function of instance builder represented by this {@linkplain Selectable}.
     *
     * @return instance builder function.
     */
    public InstanceBuilder<T> function();
}
