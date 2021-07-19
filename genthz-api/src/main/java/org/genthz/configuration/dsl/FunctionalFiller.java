package org.genthz.configuration.dsl;

import org.genthz.context.Context;

import java.util.function.BiFunction;

public interface FunctionalFiller<T> extends Selectable<T> {
    /**
     * Function of instance builder represented by this {@linkplain Selectable}.
     *
     * @return instance builder function.
     */
    public BiFunction<Context<T>, T, T> function();
}
