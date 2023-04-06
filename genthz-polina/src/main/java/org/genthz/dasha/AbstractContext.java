package org.genthz.dasha;

import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.Node;
import org.genthz.context.Stage;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Stream;

class AbstractContext<T, N> implements Context<T, N> {
    private final ContextFactory contextFactory;

    private final InstanceAccessor<T> instanceAccessor;

    private final Node<N> node;

    private final Context up;

    private final Type type;

    private Stage stage = Stage.NEW;

    public AbstractContext(ContextFactory contextFactory,
                           InstanceAccessor<T> instanceAccessor,
                           Node<N> node,
                           Context up,
                           Type type) {
        this.contextFactory = Objects.requireNonNull(contextFactory);
        this.instanceAccessor = Objects.requireNonNull(instanceAccessor);
        this.node = Objects.requireNonNull(node);
        this.up = up;
        this.type = type;
    }

    @Override
    public <P, C extends Context<P, ?>> C up() {
        return (C) this.up;
    }

    @Override
    public Stream<Context> ups() {
        return this.up != null ? StreamUtil.of(this.up, Context::up) : Stream.empty();
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
    public T get() {
        return this.instanceAccessor.get();
    }

    @Override
    public void set(T value) {
        this.instanceAccessor.set(value);
    }

    @Override
    public void init() {
        this.instanceAccessor.init();
    }

    @Override
    public T instance() {
        return this.get();
    }

    @Override
    public N node() {
        return this.node.node();
    }

    @Override
    public Type type() {
        return this.type;
    }
}
