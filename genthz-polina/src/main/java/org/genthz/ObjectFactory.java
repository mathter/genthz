/*
 * GenThz - testing becomes easier
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

import org.genthz.context.context.Context;

/**
 * This main interface of the engine. Object of this type is entry point for object generation.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 3.0.0
 * @since 3.0.0
 */
public interface ObjectFactory {

    /**
     * Method builds object of type represented by parameter <code>clazz</code>.
     *
     * @param clazz class of the object to be created.
     * @param <T>   type of the object.
     * @return object instance type of <code>T</code>.
     */
    public <T> T build(Class<T> clazz);

    /**
     * Method builds object of type represented according to {@linkplain Context} description of the object to be created.
     *
     * @param context description of the object to be create.
     * @param <T>     type of the object.
     * @return object instance type of <code>T</code>.
     */
    public <T> Context<T> build(Context<T> context);
}
