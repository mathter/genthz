package org.genthz.context;

import java.lang.reflect.Type;
import java.util.Collection;

public interface ContextFactory {
    <T> InstanceContext<T, Void> context(Class<T> type, Type... genericArgTypes);

    <U> Collection<InstanceContext> contexts(InstanceContext<U, ?> up);

    <U, T, N, L, LN> InstanceContext<T, N> context(InstanceContext<U, ?> up,
                                                   int order,
                                                   Class<T> type,
                                                   InstanceContext<L, LN> left);
}
