package org.genthz.context;

public interface IndexedContext<T, PT, P extends Context<?, ?, ?> & Instance<PT>>
        extends ConstructorContext<T, P, Integer>, Instance<T>, Accessor<T> {
    public int position();
}
