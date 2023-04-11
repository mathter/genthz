package org.genthz.dasha.context;

import org.genthz.context.*;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.stream.Stream;

class DashaContext<T, N> implements InstanceContext<T, N> {
    private final ContextFactory contextFactory;

    private final InstanceAccessor<T> instanceAccessor;

    private final Node<N> node;

    private final Context up;

    private final Type type;

    private final InstanceContext left;

    private Stage stage = Stage.NEW;

    public DashaContext(ContextFactory contextFactory,
                        InstanceAccessor<T> instanceAccessor,
                        Node<N> node,
                        Context up,
                        Type type) {
        this(
                contextFactory,
                instanceAccessor,
                node,
                up,
                null,
                type
        );
    }

    public DashaContext(ContextFactory contextFactory,
                        InstanceAccessor<T> instanceAccessor,
                        Node<N> node,
                        Context up,
                        InstanceContext left,
                        Type type) {
        this.contextFactory = Objects.requireNonNull(contextFactory);
        this.instanceAccessor = Objects.requireNonNull(instanceAccessor);
        this.node = Objects.requireNonNull(node);
        this.up = up;
        this.left = left;
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

    @Override
    public <L, LN> InstanceContext<L, LN> left() {
        return this.left;
    }

    public InstanceAccessor<T> getInstanceAccessor() {
        return instanceAccessor;
    }
}
