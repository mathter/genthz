package org.genthz.dasha.context;

import org.genthz.context.Accessor;
import org.genthz.context.Instance;

interface InstanceAccessor<T> extends Accessor<T>, Instance<T> {
    @Override
    default T instance() {
        return this.get();
    }
}
