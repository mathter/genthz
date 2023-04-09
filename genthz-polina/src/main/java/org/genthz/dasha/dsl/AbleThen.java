package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.dsl.Selectable;
import org.genthz.dsl.Selector;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

class AbleThen extends Able implements InstanceBuilderThen, FillerThen {
    public AbleThen(Selector parent) {
        super(parent);
    }

    @Override
    public <T> Selectable filler(Filler<T> function) {
        return null;
    }

    @Override
    public <T> Selectable instanceBuilder(InstanceBuilder<T> function) {
        return null;
    }

    @Override
    public boolean test(Context context) {
        throw new UnsupportedOperationException();
    }
}
