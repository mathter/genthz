package org.genthz.dasha;

import org.genthz.Defaults;

public class DashaDefaults implements Defaults {
    private int defaultCollectionSize = 5;

    @Override
    public int defaultCollectionSize() {
        return this.defaultCollectionSize;
    }
}
