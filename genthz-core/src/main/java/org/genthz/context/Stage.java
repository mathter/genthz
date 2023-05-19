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
 * This enum represents stages of the instance creation process.
 *
 * @author mathter
 * @since 3.0.0
 */
public enum Stage {
    /**
     * This is initial stage of the instance creation process. The stage is returned by method
     * {@linkplain InstanceContext#stage()} for newly created {@linkplain InstanceContext}.
     */
    NEW,

    /**
     * This stage corresponds to creating instance by instance builder
     * (see {@linkplain org.genthz.function.InstanceBuilder}
     * or {@linkplain org.genthz.function.InstanceBuilder}).
     */
    CREATING,

    /**
     * This stage is returned by method {@linkplain InstanceContext#stage()} when
     * instance builder is completed and instance of class is created (not filled).
     */
    CREATED,

    /**
     * This stage corresponds to filling of instance by filler
     * (see {@linkplain org.genthz.function.Filler}). Instance filling process means
     * object fields filling  or elements adding to a collection ({@linkplain java.util.Collection},
     * java array or {@linkplain java.util.Map}.
     */
    FILLING,

    /**
     * This stage is returned by method {@linkplain InstanceContext#stage()} then instance creation
     * fully completed (instance created by instance builder and object fields and collections are filled).
     */
    COMPLETE
}
