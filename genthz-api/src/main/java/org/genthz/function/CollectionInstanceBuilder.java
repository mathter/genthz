package org.genthz.function;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.genthz.ObjectFactory;
import org.genthz.context.Context;
import org.genthz.context.IndexedContext;
import org.genthz.context.def.CollectionIndexedContextImpl;
import org.genthz.util.Constants;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiConsumer;

public class CollectionInstanceBuilder<T, C> implements InstanceBuilder<T> {
    private final Class<T> collectionType;

    private final Class<C> componentType;

    private final int count;

    public CollectionInstanceBuilder(Class<T> collectionType, Class<C> componentType, int count) {
        this.collectionType = collectionType;
        this.componentType = componentType;
        this.count = count;
    }

    @Override
    public T apply(Context<T> context) {
        return null;
    }

    private T buildCollectionInstance(Class<T> collectionType, Class<C> componentType, int count) {
        final T result;

        try {
            if (collectionType.isArray()) {
                result = (T) Array.newInstance(componentType, count);
            } else if (List.class.equals(collectionType)) {
                result = (T) this.defaultListClass().newInstance();
            } else if (Set.class.equals(collectionType)) {
                result = (T) this.defaultSetClass().newInstance();
            } else if (Queue.class.equals(collectionType)) {
                result = (T) this.defaultQueueClass().newInstance();
            } else if (Deque.class.equals(collectionType)) {
                result = (T) this.defaultDequeClass().newInstance();
            } else if (Collection.class.equals(collectionType)) {
                result = (T) this.defaultCollectionClass().newInstance();
            } else {
                result = collectionType.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't create collection instance!", e);
        }

        return result;
    }

    private void fillCollection(Context<T> parent, Class<C> componentType, int count) {
        final ObjectFactory objectFactory = parent.objectFactory();
        final BiConsumer<C, Integer> setter;

        if (parent.clazz().isArray()) {
            setter = (e, i) -> ((C[]) parent.node())[i] = e;
        } else if (List.class.isAssignableFrom(parent.clazz())) {
            setter = (e, i) -> ((List) parent.node()).set(i, e);
        } else {
            setter = (e, i) -> ((Collection) parent.node()).add(e);
        }

        for (int index = 0; index < count; index++) {
            final IndexedContext<C> context = new CollectionIndexedContextImpl<>(
                    objectFactory,
                    parent,
                    index,
                    componentType,
                    setter
            );

            objectFactory.build(context);
        }
    }

    protected Class<? extends Collection> defaultCollectionClass() {
        return ArrayList.class;
    }

    protected Class<? extends List> defaultListClass() {
        return ArrayList.class;
    }

    protected Class<? extends Set> defaultSetClass() {
        return HashSet.class;
    }

    protected Class<? extends Queue> defaultQueueClass() {
        return ArrayDeque.class;
    }

    protected Class<? extends Deque> defaultDequeClass() {
        return ArrayDeque.class;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
    }
}
