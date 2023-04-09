package org.genthz.dasha.context;

import java.util.Map;
import java.util.Objects;

class MapAccessor<K, V> extends ObjectInstanceAccessor<K, Integer> {
    private final Map<K, V> map;

    public MapAccessor(Integer node, Map<K, V> map) {
        super(node);
        this.map = Objects.requireNonNull(map);
    }

    public ObjectInstanceAccessor<V, K> valueAccessor() {
        return new ObjectInstanceAccessor<V, K>(null) {
            @Override
            public void set(V value) throws IllegalStateException {
                super.set(value);
                MapAccessor.this.map.put(MapAccessor.this.get(), value);
            }
        };
    }
}
