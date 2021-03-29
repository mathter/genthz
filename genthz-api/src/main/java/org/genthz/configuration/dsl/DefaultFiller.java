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

/**
 * Class for configuring default filling of objects.
 *
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DefaultFiller extends Selectable {

    /**
     * The method specifies that the field should be filled in.
     * Only this fields will be filled.
     *
     * @param field name of the object field.
     * @return itself.
     */
    public DefaultFiller including(String... field);

    /**
     * The method specifies that the field should be excluding from filling.
     * If for same field
     *
     * @param field
     * @return
     */
    public DefaultFiller excluding(String... field);

    /**
     * Method returns collection of fields should be filled.
     *
     * @return collection of fiedls.
     */
    public String[] included();

    /**
     * Method returns collection of fields should be excluging from filled.
     *
     * @return collection of fiedls.
     */
    public String[] excluded();

    /**
     * This method defines custom filler function. This function will be called after default fill is completed.
     *
     * @param function custom filler function.
     * @param <T>      type of the object.
     * @return itself.
     */
    public <T> DefaultFiller custom(Filler<T> function);

    /**
     * Method returns custom filler.
     *
     * @return custom filler function.
     */
    public Filler<?> custom();
}
