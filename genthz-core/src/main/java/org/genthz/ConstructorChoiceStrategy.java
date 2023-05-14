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
package org.genthz;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * This class is strategy for search {@linkplain Constructor} for instance creation.
 *
 * @author mathter
 * @since  3.0.0
 */
public interface ConstructorChoiceStrategy {
    /**
     * Method returns {@linkplain Constructor} for selected {@linkplain Type}.
     * If parameter {@code type} represents java array, e.g. type is instance of
     * {@linkplain Class} and method {@linkplain Class#isArray()} returns <code>true</code> or
     * parameter {@code type} is instance of {@linkplain java.lang.reflect.GenericArrayType} then this method
     * returns {@code null}.
     *
     * @param type type.
     * @param <T>  type of class.
     * @return constructor.
     */
    public <T> Constructor<T> constructor(Type type);
}
