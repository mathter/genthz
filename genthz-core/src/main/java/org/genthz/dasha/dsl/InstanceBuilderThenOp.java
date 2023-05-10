package org.genthz.dasha.dsl;

import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;

public class InstanceBuilderThenOp extends Op<SelectorOp> implements InstanceBuilderThen {
    public InstanceBuilderThenOp(SelectorOp up) {
        super(up);
    }

    @Override
    public <T> void instanceBuilder(InstanceBuilder<T> function) {
        final Selector selector = this.up().selector();
        final DashaDsl dsl = this.dsl();

        dsl.reg(selector, function);
    }
}
