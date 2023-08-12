package org.genthz;

/**
 * Interface of object factory provider.
 *
 * @since 3.1.0
 */
@FunctionalInterface
public interface ObjectFactoryProvider {
    /**
     * Method returns {@linkplain ObjectFactory}.
     *
     * @return object factory.
     */
    public ObjectFactory objectFactory();
}
