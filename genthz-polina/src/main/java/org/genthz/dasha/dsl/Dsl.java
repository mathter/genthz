package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Selector;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;

import java.util.function.Predicate;

public final class Dsl implements org.genthz.dsl.Dsl {
    public static org.genthz.dsl.Dsl instance() {
        return new Dsl();
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S custom(Predicate<Context> predicate) {
        return (S) new CustomSelector(null, predicate);
    }

    @Override
    public <S extends Selector & Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path) {
        return (S) Antrl4PathProcessor.path(null, path);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & InstanceBuilderFirst & FillerFirst> S strict(Class<T> clazz) {
        return (S) new StrictClassSelector(null, clazz);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Class<T> clazz) {
        return (S) new UnstrictClassSelector(null, clazz);
    }

    @Override
    public GenerationProvider build(GenerationProvider parent) {
        throw new UnsupportedOperationException();
    }
}
