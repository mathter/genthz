package org.genthz.simple;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class Bindings implements org.genthz.context.Bindings {
    private final Optional<org.genthz.context.Bindings> parent;

    private final Map<?, ?> data = new ConcurrentHashMap<>();

    public Bindings() {
        this(null);
    }

    public Bindings(org.genthz.context.Bindings parent) {
        this.parent = Optional.ofNullable(parent);
    }

    @Override
    public Optional<org.genthz.context.Bindings> parent() {
        return this.parent;
    }

    @Override
    public <V> V get(Object key) {
        V result;

        if ((result = (V) this.data.get(key)) == null) {
            result = (V) this.parent.map(e -> e.get(key)).orElse(null);
        } else {
            result = null;
        }

        return result;
    }

    @Override
    public <V> Bindings put(Object key, V value) {
        this.put(key, value);
        return this;
    }
}
