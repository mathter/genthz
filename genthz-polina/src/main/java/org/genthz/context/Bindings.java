package org.genthz.context;

import java.util.Optional;

public interface Bindings {
    public Optional<Bindings> up();

    public <T> T get(String key);

    public <T> void put(String key, T value);
}
