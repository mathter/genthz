/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2023 mathter@mail.ru
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
package org.genthz.context;

import org.genthz.ObjectFactory;

import java.util.stream.Stream;

/**
 * Top level context interface. This interface contains common methods for context such as
 * {@linkplain #up()} - get parent context and etc.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Context {
    /**
     * Method returns parent context, returns {@code null} if there is no parent context.
     *
     * @param <P> type of parent context.
     * @return parent context.
     */
    public <P extends Context> P up();

    /**
     * Method returns {@linkplain Stream} for enumerating of all parent context rucursively.
     *
     * @return stream of parent contexts.
     */
    public Stream<Context> ups();

    /**
     * Method returns {@linkplain ContextFactory} which creates this context.
     *
     * @return context factory for this context.
     */
    public ContextFactory contextFactory();

    /**
     * Method returns {@linkplain Bindings} for this context.
     *
     * @return
     */
    public Bindings bindings();

    /**
     * Method returns {@linkplain ContextFactory} which processes this context.
     *
     * @return object factory for this context.
     */
    public ObjectFactory objectFactory();

    /**
     * This method sets object factory for this context.
     *
     * @param objectFactory
     */
    public void objectFactory(ObjectFactory objectFactory);
}
