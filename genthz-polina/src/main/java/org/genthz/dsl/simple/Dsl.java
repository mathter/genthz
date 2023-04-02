package org.genthz.dsl.simple;

import org.genthz.GenerationProvider;
import org.genthz.context.Context;
import org.genthz.dsl.Customable;
import org.genthz.dsl.Functions;
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
    public <S extends Pathable & Strictable & Unstricable & Customable & Functions> S c(Predicate<Context<?, ?, ?>> predicate) {
        return (S) new CustomSelector(null, predicate);
    }

    @Override
    public <S extends Pathable & Strictable & Unstricable & Customable & Functions> S custom(Predicate<Context<?, ?, ?>> predicate) {
        return (S) new CustomSelector(null, predicate);
    }

    @Override
    public <S extends Selector & Pathable & Strictable & Unstricable & Customable & Functions> S path(String path) {
        return (S) Antrl4PathProcessor.path(null, path);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & Functions> S strict(Class<T> clazz) {
        return (S) new StrictClassSelector(null, clazz);
    }

    @Override
    public <T, S extends Selector & Pathable & Customable & Functions> S unstrict(Class<T> clazz) {
        return (S) new UnstrictClassSelector(null, clazz);
    }

    @Override
    public GenerationProvider build(GenerationProvider parent) {
        throw new UnsupportedOperationException();
    }
}
