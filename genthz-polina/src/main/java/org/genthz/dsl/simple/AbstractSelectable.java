package org.genthz.dsl.simple;

import org.genthz.dsl.Selectable;
import org.genthz.dsl.Selector;

abstract class AbstractSelectable<T, F> implements Selectable {
    protected final Selector selector;

    private final F function;

    public AbstractSelectable(Selector selector, F function) {
        this.selector = selector;
        this.function = function;
    }

    public Selector selector() {
        return this.selector;
    }

    public F function() {
        return this.function;
    }
}
