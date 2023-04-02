package org.genthz.dsl;

public interface Strictable {
    /**
     * The method is short alias for {@linkplain #strict(Class)}.
     */
    default public <T, S extends Selector & Pathable & Customable & Functions> S s(Class<T> clazz) {
        return this.strict(clazz);
    }

    /**
     * Method returns object for building selector chain with class check element.
     *
     * @param clazz class of the path element.
     * @param <T>   type of the path element.
     * @param <S>   type of the selector.
     * @return selector.
     */
    public <T, S extends Selector & Pathable & Customable & Functions> S strict(Class<T> clazz);
}
