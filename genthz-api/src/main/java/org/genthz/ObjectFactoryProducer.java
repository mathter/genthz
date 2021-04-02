/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.genthz;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * <p>
 * Entry class for access to generation engine. This class can be used to optain {@linkplain ObjectFactory}.
 * There is default engine can be accessed by {@linkplain #producer()} method or {@linkplain #producer(String)} with
 * parameter equals to <code>null</code>.
 * </p>
 * <p>
 * Example:
 * <pre>
 *     final ObjectFactoryProducer factoryProducer = ObjectFactoryProducer.producer(); // or ObjectFactoryProducer.producer(null);
 *     final ObjectFactory factory = factoryProducer.factory(new DefaultConfiguration());
 *
 *     String value = factory.build(String.class);
 * </pre>
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ObjectFactoryProducer {
    public static final String DEFAULT_FACTORY = "loly";

    protected ObjectFactoryProducer() {
    }

    /**
     * Method creates and returns default generation engine.
     *
     * @return generation engine.
     */
    public static final ObjectFactoryProducer producer() {
        return producer(DEFAULT_FACTORY);
    }

    /**
     * Method creates and returns the generation engine identified by the <code>id</code> parameter.
     * If <code>id</code> parameter equals <code>null</code> method call is equivalent to {@linkplain #producer()}.
     *
     * @param id generation engine identifier.
     * @return generation engine.
     * @throws ProducerNotFoundException if generation engine not found.
     */
    public static final ObjectFactoryProducer producer(String id) throws ProducerNotFoundException {
        return id != null ? StreamSupport
                .stream(ServiceLoader.load(ObjectFactoryProducer.class).spliterator(), false)
                .filter(e -> id.equals(e.id()))
                .findAny()
                .orElseThrow(() -> new ProducerNotFoundException(id))
                : producer();
    }

    /**
     * Method returns {@linkplain ObjectFactory} for the generation configuration specified by the
     * <code>configuration</code> parameter.
     *
     * @param configuration configuration.
     * @return object factory.
     */
    public abstract ObjectFactory factory(Object configuration);

    /**
     * Method returns identifier of the generation engine.
     *
     * @return identifier.
     */
    protected abstract String id();
}
