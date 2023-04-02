package org.genthz.context;

import java.util.stream.Stream;

public interface Path<N, P extends Path<?, ?>> {
    public N node();

    public P parent();

    public Stream<Path> parents();
}
