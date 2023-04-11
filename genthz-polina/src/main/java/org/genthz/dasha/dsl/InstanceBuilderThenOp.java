package org.genthz.dasha.dsl;

import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.InstanceBuilder;

public class InstanceBuilderThenOp implements InstanceBuilderThen {
    @Override
    public <T> void instanceBuilder(InstanceBuilder<T> function) {
        throw new UnsupportedOperationException();
    }
}
