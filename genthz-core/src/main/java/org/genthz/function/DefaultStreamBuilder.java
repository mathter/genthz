package org.genthz.function;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;

import java.lang.reflect.Type;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DefaultStreamBuilder<T, S extends Stream<T>> implements InstanceBuilder<S> {
    protected final ContainerSize containerSize;

    public DefaultStreamBuilder() {
        this(new DefaultsContainerSize());
    }

    public DefaultStreamBuilder(ContainerSize containerSize) {
        this.containerSize = containerSize;
    }

    @Override
    public S instance(InstanceContext<S> context) {
        final S result;
        final Type type = context.type();

        if (TypeUtils.isAssignable(type, Stream.class)) {
            final ContextFactory contextFactory = context.contextFactory();
            final ObjectFactory objectFactory = context.objectFactory();

            result = (S) contextFactory.<S, T>byStream(context, IntStream.range(0, this.containerSize.get(context)).mapToObj(e -> e))
                    .map(objectFactory::process)
                    .map(e -> e.instance());
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "Context type: %s is not an Stream!",
                            type
                    )
            );
        }

        return result;
    }
}
