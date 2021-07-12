package org.genthz.context;

import org.genthz.ObjectFactory;

import java.util.Optional;

public abstract class AbstratContext<T> implements Context<T>, Accessor<T> {
    private final ObjectFactory objectFactory;

    private final Bindings bindings;

    private final Optional<Context<?>> parent;

    private Stage stage;

    public AbstratContext(ObjectFactory objectFactory, Context<?> parent) {
        this(objectFactory, parent, null);
    }

    public AbstratContext(ObjectFactory objectFactory, Context<?> parent, Bindings bindings) {
        this.objectFactory = objectFactory;
        this.parent = Optional.ofNullable(parent);
        this.bindings = bindings != null
                ? bindings
                : this.parent.map(Context::bindings)
                .map(e -> Bindings.bindings(e))
                .orElse(Bindings.bindings());
    }

    @Override
    public Bindings bindings() {
        return null;
    }

    @Override
    public ObjectFactory objectFactory() {
        return null;
    }

    @Override
    public Stage stage() {
        return this.stage;
    }

    @Override
    public void stage(Stage value) {
        this.stage = value;
    }

    @Override
    public Optional<? extends Context<?>> parent() {
        return this.parent;
    }

    @Override
    public void setInstance(T value) {
        this.stage = Stage.INSTANCE;
    }

    @Override
    public void setFilled(T value) {
        this.stage = Stage.COMPLETE;
    }
}
