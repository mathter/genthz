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

import java.util.function.Function;

/**
 * This class represents instance builder. Instance of this class used to produce objects of type <code>T</code>.
 * By default builder create new instance only. Fields of the object filled by {@linkplain Filler}.
 * There are <em>simple</em> objects such types of <code>String</code>, <code>Integer</code> and etc. Objects of this
 * <em>simple</em> types filled directly by instance builder.
 *
 * @param <T> type of the object to be created.
 * @author <a href="mailto:mathter@mail.ru">mathter</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@FunctionalInterface
public interface InstanceBuilder<T> extends Function<Context<T>, T> {

    /**
     * Instance builder of the empty object. This builder do not create object instance.
     * This type of instance builder can be used if
     * object will be created by {@linkplain Filler}.
     *
     * @param <T> type of the object to be created.
     */
    public static class Empty<T> implements InstanceBuilder<T> {
        @Override
        public T apply(Context<T> context) {
            return null;
        }
    }
}
