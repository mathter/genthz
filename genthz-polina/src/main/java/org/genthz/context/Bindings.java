package org.genthz.context;

import java.util.Optional;

public interface Bindings {
    public Optional<Bindings> parent();

    public <V> V get(Object key);

    public <V> Bindings put(Object key, V value);
}
