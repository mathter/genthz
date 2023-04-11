package org.genthz.dasha.dsl;

import org.genthz.function.Selector;
import org.genthz.function.InstanceBuilder;

class SelectableInstanceBuilder<T> extends AbstractSelectable<T, InstanceBuilder<T>> {
    public SelectableInstanceBuilder(Selector selector, InstanceBuilder<T> function) {
        super(selector, function);
    }
}
