package org.genthz.context;

import java.lang.reflect.Type;
import java.util.stream.Stream;

public interface Context<T, N> extends Accessor<T>, Instance<T>, Node<N> {
    public Type type();

    public <P, C extends Context<P, ?>> C up();

    public Stream<Context> ups();

    public ContextFactory contextFactory();
}