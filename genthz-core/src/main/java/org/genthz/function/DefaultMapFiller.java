package org.genthz.function;

import org.genthz.ObjectFactory;
import org.genthz.context.Context;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;

import java.util.Map;
import java.util.function.Function;

public class DefaultMapFiller<K, V> extends AbstractContainerFiller<Map<K, V>> {
    public DefaultMapFiller() {
        super();
    }

    public DefaultMapFiller(Function<Context, Integer> collectionSize) {
        super(collectionSize);
    }

    @Override
    public void fill(InstanceContext<Map<K, V>> context) {
        final ContextFactory contextFactory = context.contextFactory();
        final ObjectFactory objectFactory = context.objectFactory();

        contextFactory.byMapKey(context, this.collectionSize.apply(context))
                .forEach(e -> {
                    objectFactory.process(e.getKey());
                    objectFactory.process(e.getValue());
                });
    }
}
