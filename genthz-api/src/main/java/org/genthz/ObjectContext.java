/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter@mail.ru@mail.ru
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

import java.util.function.Supplier;

/**
 * Context represents root object created by {@linkplain ObjectFactory}.
 *
 * @param <T> type of object represented by this context.
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ObjectContext<T> extends Context<T>, Supplier<T> {
}