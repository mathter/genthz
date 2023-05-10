package org.genthz.dsl;

import java.lang.reflect.Type;

public interface Strictable {
    /**
     * The method is short alias for {@linkplain #strict(Type, Type...)} )}.
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S s(Type type, Type... genericTypeArgs) {
        return this.strict(type, genericTypeArgs);
    }

    /**
     * Method returns object for building selector chain with class check element.
     *
     * @param type class of the path element.
     * @param <T>   type of the path element.
     * @param <S>   type of the selector.
     * @return selector.
     */
    public <T, S extends Pathable & Customable & InstanceBuilderFirst & FillerFirst> S strict(Type type, Type... genericTypeArgs);
}
