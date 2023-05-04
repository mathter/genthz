package org.genthz.function;

import org.genthz.context.InstanceContext;

public class UnitFiller<T> implements Filler<T> {
    public static final Filler INSTANCE = new UnitFiller();

    @Override
    public void fill(InstanceContext<T> context) {
        // Do nothing.
    }
}
