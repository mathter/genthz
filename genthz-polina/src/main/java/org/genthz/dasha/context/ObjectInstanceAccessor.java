package org.genthz.dasha.context;

class ObjectInstanceAccessor<T> extends AbstractAccessor<T> implements InstanceAccessor<T>{
    private T object;

    @Override
    public T get() {
        return this.object;
    }

    @Override
    public void set(T value) throws IllegalStateException {
        super.set(value);
        this.object = value;
    }
}
