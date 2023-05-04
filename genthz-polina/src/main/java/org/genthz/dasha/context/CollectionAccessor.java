package org.genthz.dasha.context;

import java.util.Collection;
import java.util.Objects;

class CollectionAccessor<T> extends NodeObjectInstanceAccessor<T, Integer> {
    private final Collection<T> container;

    public CollectionAccessor(Integer node, Collection<T> container) {
        super(node);
        this.container = Objects.requireNonNull(container);
    }

    @Override
    public void set(T value) throws IllegalStateException {
        super.set(value);
        this.container.add(value);
    }
}
