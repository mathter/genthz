package org.genthz.dsl.simple;

import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.Functions;
import org.genthz.dsl.Pathable;
import org.genthz.dsl.Selectable;
import org.genthz.dsl.Selector;
import org.genthz.dsl.Strictable;
import org.genthz.dsl.Unstricable;
import org.genthz.function.DefaultFiller;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.util.function.Predicate;

abstract class Able extends AbstractSelector implements Pathable, Strictable, Unstricable, Customable, Functions {
    protected Able(Selector parent) {
        super(parent);
    }

    @Override
    public <T extends Pathable & Strictable & Unstricable & Customable & Functions> T custom(Predicate<Context<?, ?, ?>> predicate) {
        return (T) new CustomSelector(this, predicate);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & Functions> S strict(Class<T> clazz) {
        return (S) new StrictClassSelector(this, clazz);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & Functions> S unstrict(Class<T> clazz) {
        return (S) new UnstrictClassSelector(this, clazz);
    }

    @Override
    public <S extends Selector & Pathable & Strictable & Unstricable & Customable & Functions> S path(String path) {
        return (S) Antrl4PathProcessor.path(this, path);
    }

    @Override
    public <T> Selectable instanceBuilder(InstanceBuilder<T> function) {
        return new SelectableInstanceBuilder<>(this, function);
    }

    @Override
    public <T> Selectable simple() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Selectable simple(InstanceBuilder<T> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Selectable filler(Filler<T> function) {
        return new SelectableFiller<>(this, new DefaultFiller<>());
    }
}
