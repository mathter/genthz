package org.genthz.configuration.dsl.function;

import org.genthz.ObjectFactory;
import org.genthz.configuration.Filler;
import org.genthz.context.context.Context;

import java.util.Collection;

public class DefaultCollectionFiller<T extends Collection> implements Filler<T> {
    @Override
    public T apply(Context<T> context, T value) {
        final ObjectFactory objectFactory = context.objectFactory();



        return value;
    }
}
