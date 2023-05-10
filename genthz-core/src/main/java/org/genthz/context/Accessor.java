package org.genthz.context;

public interface Accessor<T> {
    public Stage stage();

    public void stage(Stage stage);

    /**
     * The method returns value.
     *
     * @return
     */
    public T get();

    /**
     * Set value and set stage...
     * First call move from {@linkplain Stage#CREATING} stage to {@linkplain Stage#CREATED} one.
     * Second call move from {@linkplain Stage#CREATED} stage to {@linkplain Stage#COMPLETE} one.
     *
     * @param value new value of the object.
     * @throws IllegalStateException if stage is invalid.
     */
    public void set(T value) throws IllegalStateException;
}
