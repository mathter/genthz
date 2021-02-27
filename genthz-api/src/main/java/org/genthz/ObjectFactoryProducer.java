package org.genthz;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

public abstract class ObjectFactoryProducer {
    public static final String DEFAULT_FACTORY = "loly";

    public static final ObjectFactoryProducer producer() {
        return producer(DEFAULT_FACTORY);
    }

    public static final ObjectFactoryProducer producer(String id) {
        return id != null ? StreamSupport
                .stream(ServiceLoader.load(ObjectFactoryProducer.class).spliterator(), false)
                .filter(e -> id.equals(e.id()))
                .findAny()
                .orElseThrow(() -> new ProducerNotFoundException(id))
                : producer();
    }

    protected ObjectFactoryProducer() {
    }

    public abstract ObjectFactory factory(Object configuration);

    protected abstract String id();
}
