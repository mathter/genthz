package org.genthz.loly.context;

import org.genthz.Context;
import org.genthz.ObjectFactory;
import org.genthz.loly.reflect.Accessor;

import java.util.Collection;

public class ConstructorContext<T> extends ValueContext<T> implements Accessor<T> {

    private final Class<T> clazz;

    private T object;

    public ConstructorContext(Class<T> clazz, ObjectFactory objectFactory, ValueContext<?> parent) {
        super(objectFactory, parent);
        this.clazz = clazz;
    }

    @Override
    public Class<T> clazz() {
        return this.clazz;
    }

    @Override
    public T node() {
        return this.object;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Collection<? extends Context<?>> childs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get() {
        return this.object;
    }

    @Override
    public void setInstance(T value) {
        this.object = value;
        super.setStage(Stage.INITIALIZATION);
    }

    @Override
    public void setFilled(T value) {
        this.object = value;
        super.setStage(Stage.COMPLETE);
    }
}
