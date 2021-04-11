package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Description;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class NegateSelector extends Selector {

    public NegateSelector(
            String name,
            Function<Context<?>, Long> metrics,
            Optional<Selector> next,
            Predicate<Context<?>> predicate,
            Description description
    ) {
        super(name, metrics, next, predicate, description);
    }
}
