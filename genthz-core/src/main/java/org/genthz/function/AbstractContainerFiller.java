package org.genthz.function;

public abstract class AbstractContainerFiller<T> implements Filler<T> {
    protected final ContainerSize containerSize;

    public AbstractContainerFiller() {
        this(new DefaultsContainerSize());
    }

    public AbstractContainerFiller(ContainerSize<?> containerSize) {
        this.containerSize = containerSize;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
                .append('{')
                .append("collectionSize=")
                .append(this.containerSize)
                .append('}')
                .toString();
    }
}
