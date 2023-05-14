package org.genthz.function;

import org.genthz.context.Context;

import java.util.function.Function;

public abstract class AbstractContainerFiller<T> implements Filler<T> {
    protected final Function<Context, Integer> collectionSize;

    public AbstractContainerFiller() {
        this(ctx -> ctx.objectFactory().generationProvider().defaults().defaultCollectionSize());
    }

    public AbstractContainerFiller(Function<Context, Integer> collectionSize) {
        this.collectionSize = collectionSize;
    }
}
