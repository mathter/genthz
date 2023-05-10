package org.genthz.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public interface ContextFactory {
    default <T> InstanceContext<T> single(Class<T> type, Type... genericArgTypes) {
        return this.single(null, type, genericArgTypes);
    }

    public <T> InstanceContext<T> single(Bindings bindings, Class<T> type, Type... genericArgTypes);

    public <T> List<InstanceContext> byConstructor(InstanceContext<T> up, Constructor constructor);

    public <T, E> List<NodeInstanceContext<E, Integer>> byCollection(InstanceContext<T> up, int count);

    public <T, E> List<NodeInstanceContext<E, Integer>> byArray(InstanceContext<T> up, int count);

    public <T> Collection<NodeInstanceContext<?, String>> byProperties(InstanceContext<T> up);
}
