package org.genthz.configuration.dsl;

import org.genthz.Filler;

public interface FunctionalFiller<T> extends Selectable {
    /**
     * Function of instance builder represented by this {@linkplain Selectable}.
     *
     * @return instance builder function.
     */
    public Filler<T> function();
}
