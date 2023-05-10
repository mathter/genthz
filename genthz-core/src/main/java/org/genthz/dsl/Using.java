package org.genthz.dsl;

import java.util.function.Consumer;

public interface Using<T> {
    public void use(Consumer<T> consumer);
}
