package org.genthz.dasha.dsl;

import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.dsl.Pathable;
import org.genthz.function.DefaultInstancebuilder;
import org.genthz.function.Selector;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;
import org.genthz.function.UnitFiller;

import java.lang.reflect.Type;
import java.util.function.Predicate;

class PathOp extends SelectorOp implements Pathable, Strictable, Unstricable, Customable, InstanceBuilderFirst, FillerFirst {
    private final String path;

    public PathOp(SelectorOp up, String path) {
        super(up);
        this.path = path;
    }

    @Override
    public Selector selector() {
        return Antrl4PathProcessor.path(this.up() != null ? this.up().selector() : null, this.path);
    }

    @Override
    public <T> InstanceBuilderThen filler(Filler<T> function) {
        this.dsl().reg(this.up().selector(), function);

        return new InstanceBuilderThenOp(this.up());
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
