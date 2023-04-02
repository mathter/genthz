package org.genthz.dsl;

import org.genthz.context.Context;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * The interface represents predicate for {@linkplain Context}. Method {@linkplain Predicate#test(Object)} returns
 * <code>true</code> if {@linkplain Context} satisfies the predicate conditions.
 */
public interface Selector extends Predicate<Context<?, ?, ?>>, Named<Selector>, Metric<Selector> {
    public Optional<Selector> up();
}
