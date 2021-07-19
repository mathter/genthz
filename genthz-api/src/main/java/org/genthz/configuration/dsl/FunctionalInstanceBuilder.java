package org.genthz.configuration.dsl;

import org.genthz.context.Context;

import java.util.function.Function;

public interface FunctionalInstanceBuilder<T> extends Selectable<T> {
    /**
     * Function of instance builder represented by this {@linkplain Selectable}.
     *
     * @return instance builder function.
     */
    public Function<Context<T>, T> function();
}
