package org.genthz.context;

import java.lang.reflect.Type;

public interface Typeable {
    /**
     * Method returns {@linkplain Type} of the instance will be created.
     *
     * @return type of the instance.
     */
    public Type type();
}
