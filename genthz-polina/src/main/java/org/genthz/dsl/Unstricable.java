package org.genthz.dsl;

public interface Unstricable {
    /**
     * The method is short alias for {@linkplain #unstrict(Class)} (Class)}.
     */
    default public <T, S extends Selector & Pathable & Customable & InstanceBuilderFirst & FillerFirst> S us(Class<T> clazz) {
        return this.unstrict(clazz);
    }

    /**
     * Method returns object for building selector chain with class check element.
     *
     * @param clazz class of the path element.
     * @param <T>   type of the path element.
     * @param <S>   type of the selector.
     * @return selector.
     */
    public <T, S extends Selector & Pathable & Customable & InstanceBuilderFirst & FillerFirst> S unstrict(Class<T> clazz);
}
