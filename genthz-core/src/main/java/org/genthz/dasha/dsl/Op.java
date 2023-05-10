package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.function.Selector;

import java.util.Collection;

abstract class Op<T extends Op<T>> {
    private final T up;

    public Op(T up) {
        this.up = up;
    }

    public T up() {
        return up;
    }

    public abstract Collection<Pair<Selector, ?>> op();

    public DashaDsl dsl() {
        final DashaDsl result;

        if (this.up != null) {
            result = this.up.dsl();
        } else {
            throw new IllegalStateException();
        }

        return result;
    }
}
