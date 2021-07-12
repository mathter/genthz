package org.genthz.context;

import org.genthz.ObjectFactory;

public class AbstractConstructorContext<T> extends AbstratContext<T> implements ConstructorContext<T>, Accessor<T> {
    private final int parameterIndex;

    private final Class<T> clazz;

    private T value;

    public AbstractConstructorContext(ObjectFactory objectFactory, Context<?> parent, int parameterIndex, Class<T> clazz) {
        this(objectFactory, parent, null, parameterIndex, clazz);
    }

    public AbstractConstructorContext(ObjectFactory objectFactory, Context<?> parent, Bindings bindings, int parameterIndex, Class<T> clazz) {
        super(objectFactory, parent, bindings);
        this.parameterIndex = parameterIndex;
        this.clazz = clazz;
    }

    @Override
    public Class<T> clazz() {
        return this.clazz;
    }

    @Override
    public T node() {
        return this.value;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public int index() {
        return this.parameterIndex;
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void setInstance(T value) {
        super.setInstance(value);
        this.value = value;
    }

    @Override
    public void setFilled(T value) {
        super.setFilled(value);
        this.value = value;
    }
}
