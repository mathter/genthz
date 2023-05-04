package org.genthz.dasha.dsl;

import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.function.DefaultInstancebuilder;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.Selector;
import org.genthz.function.UnitFiller;

public class InstanceBuilderFirstOp extends Op<SelectorOp> implements InstanceBuilderFirst {
    public InstanceBuilderFirstOp(SelectorOp up) {
        super(up);
    }

    @Override
    public <T> FillerThen instanceBuilder(InstanceBuilder<T> function) {
        this.dsl().reg(this.up().selector(), function);

        return new FillerThenOp(this.up());
    }

    @Override
    public <T> void simple() {
        final Selector selector = this.up().selector();
        final DashaDsl dsl = this.dsl();

        dsl.reg(selector, new DefaultInstancebuilder());
        dsl.reg(selector, UnitFiller.INSTANCE);
    }

    @Override
    public <T> void simple(InstanceBuilder<T> function) {
        final Selector selector = this.up().selector();
        final DashaDsl dsl = this.dsl();

        dsl.reg(selector, function);
        dsl.reg(selector, UnitFiller.INSTANCE);
    }
}
