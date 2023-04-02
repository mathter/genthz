package org.genthz.context;

public interface Accessor<T> {
    public T get();

    public void set(T value);

    public Stage stage();

    public void stage(Stage value);

    public ContextFactory contextFactory();
}
