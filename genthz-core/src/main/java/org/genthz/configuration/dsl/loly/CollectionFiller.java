package org.genthz.configuration.dsl.loly;

import org.genthz.Filler;

public class CollectionFiller<T, C> extends Selectable implements org.genthz.configuration.dsl.CollectionFiller<T, C> {

    private final Class<T> collectionClass;

    private final Class<C> componentClass;

    private final int count;

    private org.genthz.Filler<T> function;

    private org.genthz.Filler<C> componentFunction;

    public CollectionFiller(Selector selector, Class<T> collectionClass, Class<C> componentClass, int count) {
        super(selector);
        this.collectionClass = collectionClass;
        this.componentClass = componentClass;
        this.count = count;
    }

    @Override
    public Class<T> collectionClass() {
        return this.collectionClass;
    }

    @Override
    public Class<C> componentClass() {
        return this.componentClass;
    }

    @Override
    public int count() {
        return this.count;
    }

    @Override
    public Object function() {
        return this.function;
    }

    @Override
    public CollectionFiller<T, C> custom(org.genthz.Filler<T> function) {
        this.function = function;
        return this;
    }

    @Override
    public Filler<T> custom() {
        return this.function;
    }

    @Override
    public org.genthz.configuration.dsl.CollectionFiller<T, C> componentCustom(Filler<C> function) {
        this.componentFunction = function;
        return this;
    }

    @Override
    public Filler<C> componentCustom() {
        return this.componentFunction;
    }
}
