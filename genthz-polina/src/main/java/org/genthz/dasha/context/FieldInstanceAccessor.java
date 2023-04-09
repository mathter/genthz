package org.genthz.dasha.context;

import org.genthz.context.Instance;
import org.genthz.context.Node;
import org.genthz.reflection.Util;

import java.lang.reflect.Field;
import java.util.Objects;

public class FieldInstanceAccessor<T> extends AbstractAccessor<T>
        implements InstanceAccessor<T>, Node<String> {
    private final Instance<T> upInstance;

    private final Field field;

    public FieldInstanceAccessor(Instance<T> upInstance, Field field) {
        this.upInstance = Objects.requireNonNull(upInstance);
        this.field = Objects.requireNonNull(field);
    }

    @Override
    public T get() {
        return Util.getFieldValue(this.field, this.upInstance.instance());
    }

    @Override
    public void set(T value) throws IllegalStateException {
        super.set(value);
        Util.setFieldValue(this.field, this.upInstance.instance(), value);
    }

    @Override
    public String node() {
        return this.field.getName();
    }
}
