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

/**
 * This main interface of the engine. Object of this type is entry point for object generation.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ObjectFactory {

    /**
     * Method builds object of type represented by parameter <code>clazz</code>.
     *
     * @param clazz class of the object to be created.
     * @param <T>   type of the object.
     * @return object instance type of <code>T</code>.
     */
    public default <T> T build(Class<T> clazz) {
        return (T) this.build(Spec.of(clazz)).get();
    }

    /**
     * Method builds object of type represented according to {@linkplain Spec} description of the object to be created.
     *
     * @param initContext description of the object to be create.
     * @param <T>         type of the object.
     * @return object instance type of <code>T</code>.
     */
    public <T> ObjectContext<T> build(Spec<T> initContext);

    /**
     * Method returns {@linkplain InstanceBuilder} corresponts to context or default instance builder.
     *
     * @param context context the context for the search to be performed.
     * @param <T>     type of the object.
     * @return instance builder.
     */
    public <T> InstanceBuilder<T> instanceBuilder(Context<T> context);

    /**
     * Method returns {@linkplain Filler} corresponts to context or default filler.
     *
     * @param context context the context for the search to be performed.
     * @param <T>     type of the object.
     * @return filler.
     */
    public <T> Filler<T> filler(Context<T> context);
}
