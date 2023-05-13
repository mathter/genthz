package org.genthz.dasha.dsl;

import org.apache.commons.lang3.tuple.Pair;
import org.genthz.context.InstanceContext;
import org.genthz.dsl.Customable;
import org.genthz.dsl.FillerFirst;
import org.genthz.dsl.FillerThen;
import org.genthz.dsl.InstanceBuilderFirst;
import org.genthz.dsl.InstanceBuilderThen;
import org.genthz.dsl.Metric;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.dsl.Using;
import org.genthz.function.DefaultInstanceBuilderConsumer;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilderConsumer;
import org.genthz.function.Selector;
import org.genthz.function.UnitFiller;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

class PathOp<T> extends SelectorOp<PathOp<T>> implements Pathable, Strictable, Unstricable, Customable, InstanceBuilderFirst<T>, FillerFirst<T>, Metric<PathOp<T>>, Using<PathOp> {
    private final String path;

    public PathOp(SelectorOp up, String path) {
        super(up);
        this.path = path;
    }

    @Override
    public Selector selector() {
        return this.setTo(Antrl4PathProcessor.path(this.up() != null ? this.up().selector() : null, this.path));
    }

    @Override
    public InstanceBuilderThen filler(Filler<T> function) {
        return new InstanceBuilderThenOp(this.up(), function);
    }

    @Override
    public FillerThen instanceBuilder(InstanceBuilderConsumer<T> function) {
        return new FillerThenOp(this.up(), function);
    }

    @Override
    public void simple() {
        this.dsl().reg(new SimpleOp(this, new DefaultInstanceBuilderConsumer<>(), UnitFiller.INSTANCE));
    }

    @Override
    public void simple(InstanceBuilderConsumer<T> function) {
        this.dsl().reg(new SimpleOp(this, function, UnitFiller.INSTANCE));
    }

    @Override
    public <T, S extends Pathable & Strictable & Unstricable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S custom(Predicate<InstanceContext<T>> predicate) {
        return (S) new CustomOps(this, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path) {
        return (S) new PathOp(this, path);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S strict(Type type, Type... genericTypeArgs) {
        return (S) new StrictTypeOp(this, type, genericTypeArgs);
    }

    @Override
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S unstrict(Type type, Type... genericTypeArgs) {
        return (S) new UnstrictTypeOp(this, type, genericTypeArgs);
    }

    @Override
    public void use(Consumer<PathOp> consumer) {
        Objects.requireNonNull(consumer).accept(this);
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        return Collections.emptyList();
    }
}
