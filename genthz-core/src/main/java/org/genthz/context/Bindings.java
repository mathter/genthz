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
package org.genthz.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class contains key-value pairs.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Bindings {
    /**
     * This method returns parent bindings. Can return {@code empty}.
     *
     * @return optional with parent bindings.
     */
    public Optional<Bindings> up();

    /**
     * Method returns object for specific {@code key}. If value not found
     * method try search in parent bindings.
     *
     * @param key key.
     * @param <T> type of returned value.
     * @return value.
     * @see #up()
     */
    public <T> T get(String key);

    /**
     * Method puts {@code value} corresponding to the specific {@code key}.
     *
     * @param key   key.
     * @param value value.
     * @param <T>   type of the value.
     */
    public <T> void put(String key, T value);

    /**
     * Method create new empty bindings.
     *
     * @return empty bindings.
     */
    public static Bindings bindings() {
        return new Inner(null);
    }

    /**
     * Method creates new empty bindings with parent one.
     *
     * @param up parent bindings.
     * @return empty bindings with parent one.
     */
    public static Bindings bindings(Bindings up) {
        return new Inner(up);
    }

    /**
     * Method creates new bindings with one key-value pair.
     *
     * @param key   key.
     * @param value value.
     * @return new bingings.
     */
    public static Bindings bindings(String key, Object value) {
        final Bindings bindings = new Inner(null);
        bindings.put(key, value);

        return bindings;
    }

    /**
     * Method creates new bindings with two key-value pairs.
     *
     * @param key0   first key.
     * @param value0 first value.
     * @param key1   second key.
     * @param value1 second value.
     * @return new bindings.
     */
    public static Bindings bindings(String key0, Object value0, String key1, Object value1) {
        final Bindings bindings = new Inner(null);
        bindings.put(key0, value0);
        bindings.put(key1, value1);

        return bindings;
    }

    /**
     * Method creates new bindings with three key-value pairs.
     *
     * @param key0   first key.
     * @param value0 first value.
     * @param key1   second key.
     * @param value1 second value.
     * @param key2   third key.
     * @param value2 third value.
     * @return new bindings.
     */
    public static Bindings bindings(String key0, Object value0, String key1, Object value1, String key2, Object value2) {
        final Bindings bindings = new Inner(null);
        bindings.put(key0, value0);
        bindings.put(key1, value1);
        bindings.put(key2, value2);

        return bindings;
    }

    class Inner implements Bindings {
        private final Optional<Bindings> up;

        private final Map<String, Object> map = new HashMap<>();

        private Inner(Bindings up) {
            this.up = Optional.ofNullable(up);
        }

        @Override
        public Optional<Bindings> up() {
            return this.up;
        }

        @Override
        public <T> T get(String key) {
            T result;

            if ((result = (T) this.map.get(key)) == null) {
                result = (T) this.up.map(e -> e.get(key)).orElse(null);
            }

            return result;
        }

        @Override
        public <T> void put(String key, T value) {
            this.map.put(key, value);
        }
    }
}
