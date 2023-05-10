package org.genthz.dsl;

public interface Named<T extends Named<T>> {
    public String name();

    public T name(CharSequence name);
}
