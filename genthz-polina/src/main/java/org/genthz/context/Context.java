package org.genthz.context;

import org.genthz.Now;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/**
 * Context of object creation.
 *
 * @param <T> type of the current object represented by the context.
 * @param <P> type of the parent context.
 * @param <N> type of the path node.
 */
public interface Context<T, P extends Context<?, ?, ?>, N> extends Path<N, P> {
    public Type type();

    public Map<TypeVariable, Type> genericAtribution();

    public Bindings bindings();

    public Now now();
}