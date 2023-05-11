package org.genthz.function;

import org.genthz.context.Context;
import org.genthz.context.InstanceContext;
import org.genthz.dsl.Metric;
import org.genthz.dsl.Named;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * The interface represents predicate for {@linkplain InstanceContext}. Method {@linkplain Predicate#test(Object)} returns
 * <code>true</code> if {@linkplain InstanceContext} satisfies the predicate conditions.
 */
public interface Selector extends Predicate<Context>, Named<Selector>, Metric<Selector> {
    public Optional<Selector> up();
}