package org.genthz.context;

import java.util.Optional;

public interface Bindings {
    public Optional<Bindings> up();

    public <T> T get(String key);

    public <T> void put(String key, T value);

    public static Bindings bindings() {
        return new Inner(null);
    }

    public static Bindings bindings(Bindings up) {
        return new Inner(up);
    }

    public static Bindings bindings(String key, Object value) {
        final Bindings bindings = new Inner(null);
        bindings.put(key, value);

        return bindings;
    }

    public static Bindings bindings(String key0, Object value0, String key1, Object value1) {
        final Bindings bindings = new Inner(null);
        bindings.put(key0, value0);
        bindings.put(key1, value1);

        return bindings;
    }

    public static Bindings bindings(String key0, Object value0, String key1, Object value1, String key2, Object value2) {
        final Bindings bindings = new Inner(null);
        bindings.put(key0, value0);
        bindings.put(key1, value1);
        bindings.put(key2, value2);

        return bindings;
    }

    class Inner implements Bindings {
        private final Optional<Bindings> up;

        private Inner(Bindings up) {
            this.up = Optional.ofNullable(up);
        }

        @Override
        public Optional<Bindings> up() {
            return this.up;
        }

        @Override
        public <T> T get(String key) {
            return null;
        }

        @Override
        public <T> void put(String key, T value) {

        }
    }
}
