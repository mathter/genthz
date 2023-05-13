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

class StrictTypeOp<T> extends TypeOp<StrictTypeOp<T>> implements Pathable, Customable, InstanceBuilderFirst<T>, FillerFirst<T>, Metric<StrictTypeOp<T>>, Using<StrictTypeOp> {
    public StrictTypeOp(SelectorOp up, Type type, Type... genericTypeArgs) {
        super(up, type, genericTypeArgs);
    }

    @Override
    public Selector selector() {
        return this.setTo(new StrictClassSelector(this.up() != null ? this.up().selector() : null, this.type));
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
    public InstanceBuilderThen<T> filler(Filler<T> function) {
        return new InstanceBuilderThenOp(this, function);
    }

    @Override
    public FillerThen instanceBuilder(InstanceBuilderConsumer<T> function) {
        return new FillerThenOp(this, function);
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
    public void use(Consumer<StrictTypeOp> consumer) {
        Objects.requireNonNull(consumer).accept(this);
    }

    @Override
    public Collection<Pair<Selector, ?>> op() {
        return Collections.emptyList();
    }
}
