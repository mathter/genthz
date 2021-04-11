package org.genthz.loly;

import org.genthz.Context;
import org.genthz.Description;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class UpSelector extends Selector {

    public UpSelector(String name,
                      Function<Context<?>,
                              Long> metrics,
                      Optional<Selector> next,
                      Predicate<Context<?>> predicate,
                      Description description) {
        super(name, metrics, next, predicate, description);
    }

    @Override
    public boolean test(Context<?> context) {
        return this.predicate.test(context)
                && this.next.map((s) -> context.parent().map((c) -> s.test(c)).orElse(false))
                .orElse(true);
    }
}
