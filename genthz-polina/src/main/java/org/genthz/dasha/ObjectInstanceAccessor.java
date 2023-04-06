package org.genthz.dasha;

import org.genthz.context.Node;

class ObjectInstanceAccessor<T, N> extends AbstractAccessor<T> implements InstanceAccessor<T>, Node<N> {
    private final N node;

    private T object;

    public ObjectInstanceAccessor(N node) {
        this.node = node;
    }

    @Override
    public T get() {
        return this.object;
    }

    @Override
    public void set(T value) throws IllegalStateException {
        super.set(value);
        this.object = value;
    }

    @Override
    public N node() {
        return this.node;
    }
}
