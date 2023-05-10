package org.genthz.dsl;

import java.lang.reflect.Type;

public interface Unstricable {
    /**
     * The method is short alias for {@linkplain #unstrict(Type, Type...)}.
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S us(Type type, Type... genericTypeArgs) {
        return this.unstrict(type, genericTypeArgs);
    }

    /**
     * Method returns object for building selector chain with class check element.
     *
     * @param type of the path element.
     * @param <T>   type of the path element.
     * @param <S>   type of the selector.
     * @return selector.
     */
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Type type, Type... genericTypeArgs);
}
