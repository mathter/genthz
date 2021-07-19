/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
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
package org.genthz.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Bindings for Generated Engine.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Bindings {

    public static Bindings bindings(Bindings parent, Object... args) {
        final Simple result = new Simple(parent);

        if (args != null && args.length > 0) {
            if (args.length % 2 == 0) {
                for (int i = 0; i < args.length; i += 2) {
                    Object key = args[i];
                    Object value = args[i + 1];

                    result.put(key, value);
                }
            } else {
                throw new IllegalArgumentException("Even count of argument required! Now count of arguments is " + args.length);
            }
        }

        return result;
    }

    public static Bindings bindings(Object... args) {
        return Bindings.bindings(null, args);
    }

    public <T> T get(Object key);

    public <T> void put(Object key, T value);

    public void remove(Object key);

    static final class Simple implements Bindings {

        private final Bindings parent;

        private final Map<Object, Object> map;

        public Simple(Bindings parent) {
            this.map = new HashMap<>();
            this.parent = parent;
        }

        @Override
        public <T> T get(Object key) {
            T result;

            if ((result = (T) this.map.get(key)) == null) {
                if (this.parent != null) {
                    result = this.parent.get(key);
                }
            }

            return result;
        }

        @Override
        public <T> void put(Object key, T value) {
            this.map.put(key, value);
        }

        @Override
        public void remove(Object key) {
            if (this.map.containsKey(key)) {
                this.map.remove(key);
            } else {
                this.parent.remove(key);
            }
        }
    }
}
