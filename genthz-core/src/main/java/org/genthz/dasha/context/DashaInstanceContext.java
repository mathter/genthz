package org.genthz.dasha.context;

import org.genthz.ObjectFactory;
import org.genthz.context.*;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Stream;

class DashaInstanceContext<T> implements InstanceContext<T> {
    private final ContextFactory contextFactory;

    private final InstanceAccessor<T> instanceAccessor;

    private final Context up;

    private final Type type;

    private final Bindings bindings;

    private Stage stage = Stage.NEW;

    private ObjectFactory objectFactory;

    public DashaInstanceContext(ContextFactory contextFactory,
                                Bindings bindings,
                                InstanceAccessor<T> instanceAccessor,
                                Context up,
                                Type type) {
        this.contextFactory = Objects.requireNonNull(contextFactory);
        this.bindings = bindings;
        this.instanceAccessor = Objects.requireNonNull(instanceAccessor);
        this.up = up;
        this.type = type;
    }

    @Override
    public <P extends Context> P up() {
        return (P) this.up;
    }

    @Override
    public Stream<Context> ups() {
        return StreamUtil.of(this.up, Context::up);
    }

    @Override
    public ContextFactory contextFactory() {
        return this.contextFactory;
    }

    @Override
    public Stage stage() {
        return this.instanceAccessor.stage();
    }

    @Override
    public void stage(Stage stage) {
        this.instanceAccessor.stage(stage);
    }

    @Override
    public T get() {
        return this.instanceAccessor.get();
    }

    @Override
    public void set(T value) {
        this.instanceAccessor.set(value);
    }

    @Override
    public T instance() {
        return this.get();
    }

    @Override
    public Type type() {
        return this.type;
    }

    public InstanceAccessor<T> getInstanceAccessor() {
        return instanceAccessor;
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.objectFactory;
    }

    @Override
    public void objectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Bindings bindings() {
        return this.bindings;
    }
}
