package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Selector;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;

import java.util.function.Predicate;

abstract class Able
        extends AbstractSelector
        implements Pathable,
        Strictable,
        Unstricable,
        Customable {
    protected Able(Selector parent) {
        super(parent);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S custom(Predicate<Context> predicate) {
        return (S) new CustomSelector(this, predicate);
    }

    @Override
    public <S extends Selector & Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path) {
        return (S) Antrl4PathProcessor.path(this, path);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & InstanceBuilderFirst & FillerFirst> S strict(Class<T> clazz) {
        return (S) new StrictClassSelector(this, clazz);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Class<T> clazz) {
        return (S) new UnstrictClassSelector(this, clazz);
    }
}
