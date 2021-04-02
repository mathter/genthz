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
package org.genthz.configuration.dsl;

import org.genthz.Filler;
import org.genthz.InstanceBuilder;

/**
 * Interface represents producer for {@linkplain NonStrict} selector.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface NonStrictable {

    /**
     * Method creates new instance builder description for given {@linkplain Selector} or root selector.
     *
     * @param function instance builder function.
     * @param <T>      type of the object to be created.
     * @return instance builder description.
     * @see #nonstrict(InstanceBuilder, Class)
     */
    public default <T> FunctionalInstanceBuilder<T> nonstrict(InstanceBuilder<T> function) {
        return nonstrict(function, null);
    }

    /**
     * Method creates new instance builder description for given {@linkplain Selector} or root one and
     * specified classof the object to be created.
     * If the clazz parameter is zero, the method uses the return value class of the apply method of
     * the {@linkplain InstanceBuilder} interface.
     *
     * @param function instance builder function.
     * @param <T>      type of the object to be created.
     * @return instance builder description.
     */
    public <T> FunctionalInstanceBuilder nonstrict(InstanceBuilder<T> function, Class<T> clazz);

    /**
     * Method creates new {@linkplain Filler} for given {@linkplain Selector} or root selector.
     *
     * @param function filler function.
     * @param <T>      type of the object to be filled.
     * @return filler.
     * @see #nonstrict(Filler, Class)
     */
    public default <T> FunctionalFiller<T> nonstrict(Filler<T> function) {
        return nonstrict(function, null);
    }

    /**
     * Method creates new filler description for given {@linkplain Selector} or root one and
     * specified classof the object to be created.
     * If the clazz parameter is zero, the method uses the return value class of the apply method of
     * the {@linkplain Filler} interface.
     *
     * @param function filler function.
     * @param <T>      type of the object to be created.
     * @return instance builder description.
     */
    public <T> FunctionalFiller<T> nonstrict(Filler<T> function, Class<T> clazz);

    /**
     * Method returns new class based selector.
     *
     * @param clazz class.
     * @param <T>   type of the object to be created.
     * @return class based selector.
     */
    public <T> Selector nonstrict(Class<T> clazz);
}
