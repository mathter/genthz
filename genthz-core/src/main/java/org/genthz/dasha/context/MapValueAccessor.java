/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.genthz.dasha.context;

import org.genthz.context.Instance;
import org.genthz.context.MapKey;

import java.util.Map;

class MapValueAccessor<V, K> extends NodeObjectInstanceAccessor<V, MapKey<K>> {
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
