package org.genthz.function;

import org.genthz.context.Context;

public class DefaultsContainerSize<T extends Context> implements ContainerSize<T> {
    @Override
    public int get(T context) {
        return context.objectFactory().generationProvider().defaults().defaultCollectionSize();
    }

    @Override
    public String toString() {
        return "DefaultsContainerSize{}";
    }
}
