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

import org.genthz.Context;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Interface describes object generation configuration.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Configuration extends Strictable, NonStrictable, Pathable, Conditional {

    public Configuration reg(Selectable selectable);

    public default Configuration reg(Selectable... selectables) {
        this.reg(Arrays.asList(selectables));

        return this;
    }

    /**
     * Method to registration of {@linkplain Selectable} objects.
     *
     * @param selectables {@linkplain Selectable}, can't be null.
     * @return itself.
     */
    public Configuration reg(Collection<Selectable> selectables);

    /**
     * Method returns all {@linkplain Selectable} objects of this configuration.
     *
     * @return {@linkplain Selectable} objects.
     */
    public Collection<Selectable> selectables();

    /**
     * Name of this configuration.
     *
     * @return configuration name.
     */
    public String name();

    /**
     * Method creates custom selector descriped by parameter.
     *
     * @param predicate condition of the selector.
     * @param <T>       type used of the selector.
     * @return selector descriped by method paraameter.
     * @see Сustomizable#custom(Predicate)
     */
    public <T> Selector<T> custom(Predicate<Context<?>> predicate);
}
