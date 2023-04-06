package org.genthz.context;

import java.lang.reflect.Type;
import java.util.Collection;

public interface ContextFactory {
    public <T> Context<T, Void> context(Class<T> type, Type... genericArgTypes);

    public <T> Collection<Context> contexts(Context<T, ?> up);
}
