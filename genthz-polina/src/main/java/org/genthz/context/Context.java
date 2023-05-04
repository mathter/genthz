package org.genthz.context;

import org.genthz.ObjectFactory;

import java.util.stream.Stream;

public interface Context {
    public <P extends Context> P up();

    public Stream<Context> ups();

    public ContextFactory contextFactory();

    public Bindings bindings();

    public ObjectFactory objectFactory();

    public void objectFactory(ObjectFactory objectFactory);
}
