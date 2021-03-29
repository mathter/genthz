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
     * Method creates new {@linkplain InstanceBuilder} for given {@linkplain Selector} or root selector.
     *
     * @param function instance builder function.
     * @param <T>      type of the object to be created.
     * @return instance builder description.
     */
    public default <T> Selectable nonstrict(InstanceBuilder<T> function) {
        return nonstrict(function, null);
    }

    /**
     * Method creates new {@linkplain InstanceBuilder} for given {@linkplain Selector} or root selector.
     *
     * @param function instance builder function.
     * @param <T>      type of the object to be created.
     * @return instance builder description.
     */
    public <T> Selectable nonstrict(InstanceBuilder<T> function, Class<T> clazz);

    public default <T> Selectable nonstrict(Filler<T> function) {
        return nonstrict(function, null);
    }

    public <T> Selectable nonstrict(Filler<? extends T> function, Class<T> clazz);

    public <T> Selector nonstrict(Class<T> clazz);
}
