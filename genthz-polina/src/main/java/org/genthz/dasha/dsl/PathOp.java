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

class PathOp extends Op implements Pathable, Strictable, Unstricable, Customable, InstanceBuilderFirst, FillerFirst {
    private final String path;

    public PathOp(Op up, String path) {
        super(up);
        this.path = path;
    }

    @Override
    public Selector selector() {
        return Antrl4PathProcessor.path(this.up().map(Op::selector).orElse(null), this.path);
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

    @Override
    public <S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst & FillerFirst> S custom(Predicate<Context> predicate) {
        return (S) new CustomOps(this, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path) {
        return (S) new PathOp(this, path);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S strict(Type type, Type... genericTypeArgs) {
        return (S) new StrictTypeOp(this, type, genericTypeArgs);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Type type, Type... genericTypeArgs) {
        return (S) new UnstrictTypeOp(this, type, genericTypeArgs);
    }
}
