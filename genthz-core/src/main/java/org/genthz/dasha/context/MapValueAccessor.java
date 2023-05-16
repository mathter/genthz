package org.genthz.dasha.context;

import org.genthz.context.Instance;
import org.genthz.context.MapKey;

import java.util.Map;

public class MapValueAccessor<V, K> extends NodeObjectInstanceAccessor<V, MapKey<K>> {
    private final Map<K, V> container;

    public MapValueAccessor(Map<K, V> container, Instance<K> instance, int index) {
        super(() -> new MapKey<K>() {
            @Override
            public K key() {
                return instance.instance();
            }

            @Override
            public int index() {
                return index;
            }
        });
        this.container = container;
    }

    @Override
    public void set(V value) throws IllegalStateException {
        super.set(value);
        this.container.put(this.node().key(), value);
    }
}
