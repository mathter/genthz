package org.genthz.dasha.context;

import org.genthz.context.Node;

class NodeObjectInstanceAccessor<T, N> extends ObjectInstanceAccessor<T> implements Node<N> {
    private final N node;

    private T object;

    public NodeObjectInstanceAccessor(N node) {
        this.node = node;
    }

    @Override
    public N node() {
        return this.node;
    }
}
