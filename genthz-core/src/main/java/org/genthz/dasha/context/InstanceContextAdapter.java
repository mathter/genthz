package org.genthz.dasha.context;

import org.genthz.ObjectFactory;
import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.context.Stage;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Stream;

public class InstanceContextAdapter<T> implements InstanceContext<T> {
    private final InstanceContext<T> context;

    public InstanceContextAdapter(InstanceContext<T> context) {
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public Stage stage() {
        return this.context.stage();
    }

    @Override
    public void stage(Stage stage) {
        this.context.stage(stage);
    }

    @Override
    public T get() {
        return this.context.get();
    }

    @Override
    public void set(T value) throws IllegalStateException {
        this.context.set(value);
    }

    @Override
    public <P extends Context> P up() {
        return this.context.up();
    }

    @Override
    public Stream<Context> ups() {
        return this.context.ups();
    }

    @Override
    public ContextFactory contextFactory() {
        return this.context.contextFactory();
    }

    @Override
    public Bindings bindings() {
        return this.context.bindings();
    }

    @Override
    public ObjectFactory objectFactory() {
        return this.context.objectFactory();
    }

    @Override
    public void objectFactory(ObjectFactory objectFactory) {
        this.context.objectFactory(objectFactory);
    }

    @Override
    public T instance() {
        return this.context.instance();
    }

    @Override
    public Type type() {
        return this.context.type();
    }
}
