package org.genthz.dasha.context;

import org.genthz.context.Bindings;
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.Node;
import org.genthz.context.NodeInstanceContext;

import java.lang.reflect.Type;
import java.util.Objects;

class DashaNodeInstanceContext<T, N> extends DashaInstanceContext<T> implements NodeInstanceContext<T, N> {
    private final Node<N> node;

    public DashaNodeInstanceContext(ContextFactory contextFactory,
                                    InstanceAccessor<T> instanceAccessor,
                                    Context up,
                                    Type type,
                                    Node<N> node) {
        super(contextFactory, Bindings.bindings(up.bindings()), instanceAccessor, up, type);
        this.node = Objects.requireNonNull(node);
    }

    @Override
    public N node() {
        return this.node.node();
    }
}
