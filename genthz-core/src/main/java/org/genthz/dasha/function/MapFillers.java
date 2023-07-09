package org.genthz.dasha.function;

import org.genthz.function.DefaultMapFiller;
import org.genthz.function.Filler;

import java.util.Map;

/**
 * Predefined fillers for {@linkplain Map}.
 */
public abstract class MapFillers {
    /**
     * Method creates a filler for a map having selected count of an elements.
     *
     * @param size count of collection elements.
     * @param <T>  type of map.
     * @return filler.
     */
    public static <T extends Map> Filler<T> size(int size) {
        return new DefaultMapFiller(context -> size);
    }

    private MapFillers() {
    }
}
