package org.genthz.function;

import org.genthz.ObjectFactory;
import org.genthz.context.ContextFactory;
import org.genthz.context.InstanceContext;

import java.util.Map;

public class DefaultMapFiller<K, V> extends AbstractContainerFiller<Map<K, V>> {
    public DefaultMapFiller() {
        super();
    }

    public DefaultMapFiller(ContainerSize containerSize) {
        super(containerSize);
    }

    @Override
    public void fill(InstanceContext<Map<K, V>> context) {
        if (context.get() != null) {
            final ContextFactory contextFactory = context.contextFactory();
            final ObjectFactory objectFactory = context.objectFactory();

            contextFactory.byMapKey(context, this.containerSize.get(context))
                    .forEach(e -> {
                        objectFactory.process(e.getKey());
                        objectFactory.process(e.getValue());
                    });
        }
    }
}
