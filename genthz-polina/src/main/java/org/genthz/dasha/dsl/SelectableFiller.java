package org.genthz.dasha.dsl;

import org.genthz.dsl.Selector;
import org.genthz.function.Filler;

class SelectableFiller<T> extends AbstractSelectable<T, Filler<T>> {
    public SelectableFiller(Selector selector, Filler<T> function) {
        super(selector, function);
    }
}
