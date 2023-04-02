package org.genthz.function;

import org.genthz.context.ConstructorContext;
import org.genthz.context.ContextFactory;
import org.genthz.context.IndexedContext;
import org.genthz.reflection.GenericUtil;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.stream.IntStream;

public class CollectionInstanceBuilder<T extends Collection> implements InstanceBuilder<T> {
    @Override
    public T instance(ConstructorContext<T, ?, ?> context) {
        final T instance;
        final InstanceBuilder<T> instanceBuilder;
        final Class<T> clazz = GenericUtil.rawType(context.type());

        if (clazz.isInterface()) {
            if (List.class.isAssignableFrom(clazz)) {
                instanceBuilder = this.listInstanceBuilder((Class<List>) clazz);
            } else if (Set.class.isAssignableFrom(clazz)) {
                instanceBuilder = this.setInstanceBuilder((Class<Set>) clazz);
            } else if (Deque.class.isAssignableFrom(clazz)) {
                instanceBuilder = this.dequeInstanceBuilder((Class<Deque>) clazz);
            } else if (Queue.class.isAssignableFrom(clazz)) {
                instanceBuilder = this.queueInstanceBuilder((Class<Queue>) clazz);
            } else {
                instanceBuilder = this.listInstanceBuilder((Class<List>) clazz);
            }
        } else {
            instanceBuilder = InstanceBuilders.constructorWithMinArgs(clazz);
        }

        if ((instance = instanceBuilder.instance(context)) != null) {
            final ContextFactory contextFactory = context.contextFactory();
            final int count = context.now().defaults().defaultCollectionSize();

            for (int i=0;i<count;i++){
                IndexedContext<?,?,?> ctx = (IndexedContext<?, ?, ?>) contextFactory.contexts(null);
            }

        }

        return instance;
    }

    private InstanceBuilder<T> queueInstanceBuilder(Class<Queue> clazz) {
        final InstanceBuilder<T> result;

        if (TransferQueue.class.isAssignableFrom(clazz)) {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(LinkedTransferQueue.class);
        } else if (BlockingQueue.class.isAssignableFrom(clazz)) {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(ArrayBlockingQueue.class);
        } else {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(ArrayDeque.class);
        }

        return result;
    }


    private InstanceBuilder<T> dequeInstanceBuilder(Class<Deque> clazz) {
        final InstanceBuilder<T> result;

        if (BlockingDeque.class.isAssignableFrom(clazz)) {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(LinkedBlockingDeque.class);
        } else {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(ArrayDeque.class);
        }

        return result;
    }

    private InstanceBuilder<T> setInstanceBuilder(Class<Set> clazz) {
        final InstanceBuilder<T> result;

        if (NavigableSet.class.isAssignableFrom(clazz)) {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(TreeSet.class);
        } else if (SortedSet.class.isAssignableFrom(clazz)) {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(TreeSet.class);
        } else {
            result = (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(HashSet.class);
        }

        return result;
    }

    private InstanceBuilder<T> listInstanceBuilder(Class<List> clazz) {
        return (InstanceBuilder<T>) InstanceBuilders.constructorWithMinArgs(clazz);
    }

}
