package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.dsl.Pathable;
import org.genthz.function.Selector;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.lang.reflect.Type;
import java.util.function.Predicate;

public class CustomOps extends Op implements Pathable, Strictable, Unstricable, InstanceBuilderFirst, FillerFirst {
    private final Predicate<Context> predicate;

    public CustomOps(Op up, Predicate<Context> predicate) {
        super(up);
        this.predicate = predicate;
    }

    @Override
    public Selector selector() {
        return new CustomSelector(this.up().map(Op::selector).orElse(null), this.predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path) {
        return null;
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S strict(Type type, Type... genericTypeArgs) {
        return null;
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Type type, Type... genericTypeArgs) {
        return null;
    }

    @Override
    public <T> InstanceBuilderThen filler(Filler<T> function) {
        return null;
    }

    @Override
    public <T> FillerThen instanceBuilder(InstanceBuilder<T> function) {
        return null;
    }

    @Override
    public <T> void simple() {
    }

    @Override
    public <T> void simple(InstanceBuilder<T> function) {
    }
}
