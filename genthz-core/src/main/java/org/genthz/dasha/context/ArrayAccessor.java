package org.genthz.dasha.context;

public class ArrayAccessor<T> extends NodeObjectInstanceAccessor<T, Integer> {
    private final T[] container;

    public ArrayAccessor(Integer node, T[] container) {
        super(node);
        this.container = container;
    }

    @Override
    public void set(T value) throws IllegalStateException {
        super.set(value);
        this.container[this.node()] = value;
    }
}
