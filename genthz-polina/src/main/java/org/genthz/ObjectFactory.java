package org.genthz;

import org.genthz.context.Bindings;

import java.lang.reflect.Type;

public interface ObjectFactory {
    /**
     * Method creates new instance of {@linkplain Class}.
     *
     * @param clazz        class of new instance.
     * @param genericTypes generic arguments of class.
     * @param <T>          type if instance.
     * @return instance of {@linkplain Class}.
     */
    default public <T> T get(final Class<T> clazz, final Type... genericTypes) {
        return this.get(null, clazz, genericTypes);
    }

    /**
     * Method creates new instance of {@linkplain Class}.
     *
     * @param bindings     context data.
     * @param clazz        class of new instance.
     * @param genericTypes generic arguments of class.
     * @param <T>          type if instance.
     * @return instance of {@linkplain Class}.
     */
    public <T> T get(final Bindings bindings, final Class<T> clazz, final Type... genericTypes);

    public GenerationProvider generationProvider();
}
