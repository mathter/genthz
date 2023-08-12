package org.genthz.junit;

import org.genthz.context.InstanceContext;
import org.genthz.function.Filler;

public class NoFiller<T> implements Filler<T> {
    @Override
    public void fill(InstanceContext<T> context) {
        throw new UnsupportedOperationException();
    }
}
