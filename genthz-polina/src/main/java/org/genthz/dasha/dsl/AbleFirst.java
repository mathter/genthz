package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.dsl.Selectable;
import org.genthz.dsl.Selector;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

class AbleFirst extends Able implements InstanceBuilderFirst, FillerFirst {
    public AbleFirst(Selector parent) {
        super(parent);
    }

    @Override
    public <T, S extends Selectable & InstanceBuilderThen> S filler(Filler<T> function) {
        return (S) new AbleThen(this.up().orElse(null));
    }

    @Override
    public <T, S extends Selectable & FillerThen> S instanceBuilder(InstanceBuilder<T> function) {
        return (S) new AbleThen(this.up().orElse(null));
    }

    @Override
    public <T> Selectable simple() {
        return null;
    }

    @Override
    public <T> Selectable simple(InstanceBuilder<T> function) {
        return null;
    }


    @Override
    public boolean test(Context context) {
        throw new UnsupportedOperationException();
    }
}
