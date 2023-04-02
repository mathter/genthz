package org.genthz.simple;

import org.genthz.Now;
import org.genthz.context.Accessor;
import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.Path;
import org.genthz.context.Stage;
import org.genthz.context.ContextFactory;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

abstract class AbstractContext<T, P extends Context<?, ?, ?>, N> implements Context<T, P, N>, Accessor<T>, Nowable {
    private final ContextFactory contextFactory;

    private final Bindings bindings;

    private Now now;

    private final P parent;

    private final Map<TypeVariable, Type> genericAtribution;

    private final Type type;

    private Stage stage = Stage.NEW;

    public AbstractContext(ContextFactory contextFactory,
                           P parent,
                           Type type,
                           Map<TypeVariable, Type>
                                   genericAtribution) {
        this.contextFactory = contextFactory;
        this.bindings = new org.genthz.simple.Bindings(parent != null ? parent.bindings() : null);
        this.parent = parent;
        this.type = type;
        this.genericAtribution = genericAtribution != null ? genericAtribution : new HashMap<>();
    }

    @Override
    public P parent() {
        return this.parent;
    }

    @Override
    public Stream<Path> parents() {
        final Stream<Path> result;

        if (this.parent != null) {
            result = StreamUtil.of(this.parent, Path::parent);
        } else {
            result = Stream.empty();
        }

        return result;
    }

    @Override
    public Map<TypeVariable, Type> genericAtribution() {
        return this.genericAtribution;
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
    public Bindings bindings() {
        return this.bindings;
    }

    @Override
    public Type type() {
        return this.type;
    }

    @Override
    public ContextFactory contextFactory() {
        return this.contextFactory;
    }

    @Override
    public Now now() {
        return this.now;
    }

    @Override
    public void now(Now now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().toString())
                .append("{stage=")
                .append(this.stage)
                .append("}")
                .toString();
    }
}
