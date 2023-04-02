package org.genthz.simple;

import org.genthz.Defaults;

public class SimpleDefaults implements Defaults {
    private final int defaultCollectionSize;

    public SimpleDefaults() {
        this(5);
    }

    public SimpleDefaults(int defaultCollectionSize) {
        this.defaultCollectionSize = defaultCollectionSize;
    }

    @Override
    public int defaultCollectionSize() {
        return this.defaultCollectionSize;
    }
}
