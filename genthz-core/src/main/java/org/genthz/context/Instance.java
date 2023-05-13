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

/**
 * This class is used to access to the value.
 *
 * @param <T>
 */
public interface Instance<T> {
    /**
     * This method differs from the {@linkplain Accessor#get()} method
     * in that it can initiate the process of creating an object.
     *
     * @return object.
     * @see Accessor#get()
     */
    public T instance();
}
