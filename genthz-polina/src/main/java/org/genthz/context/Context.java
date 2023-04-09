package org.genthz.context;

import java.util.stream.Stream;

public interface Context {
    public <P extends Context> P up();

    public Stream< Context> ups();

    public ContextFactory contextFactory();
}
