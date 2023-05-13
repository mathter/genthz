package org.genthz.function;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.Defaults;
import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public class DefaultArrayInstanceBuilderConsumer<T> implements InstanceBuilderConsumer<T> {
    private final GenericUtil genericUtil;

    public DefaultArrayInstanceBuilderConsumer() {
        this(new GenericUtil(false));
    }

    public DefaultArrayInstanceBuilderConsumer(GenericUtil genericUtil) {
        this.genericUtil = genericUtil;
    }

    @Override
    public void instance(InstanceContext<T> context) {
        final T instance;
        final ContextFactory contextFactory = context.contextFactory();
        final ObjectFactory objectFactory = context.objectFactory();
        final Type type = context.type();

        if (TypeUtils.isArrayType(type)) {
            final Defaults defaults = objectFactory.generationProvider().defaults();
            instance = (T) Array.newInstance(
                    this.genericUtil.getRawClass(null, TypeUtils.getArrayComponentType(type)),
                    defaults.defaultCollectionSize()
            );

            context.set(instance);
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "Context type: %s is not an array!",
                            type
                    )
            );
        }
    }
}
