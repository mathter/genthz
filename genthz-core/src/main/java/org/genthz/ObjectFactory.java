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

import org.genthz.context.Bindings;
import org.genthz.context.InstanceContext;

import java.lang.reflect.Type;

/**
 * This class is entry point for instance generation.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface ObjectFactory {
    /**
     * Method creates new instance of {@linkplain Class}.
     *
     * @param clazz        class of new instance.
     * @param genericTypes generic arguments of class.
     * @param <T>          type if instance.
     * @return instance of {@linkplain Class}.
     */
    default public <T> T get(final Class<T> clazz, final Type... genericTypes) {
        return this.get(null, clazz, genericTypes);
    }

    /**
     * Method creates new instance of {@linkplain Class}.
     *
     * @param bindings     context data.
     * @param clazz        class of new instance.
     * @param genericTypes generic arguments of class.
     * @param <T>          type if instance.
     * @return instance of {@linkplain Class}.
     */
    public <T> T get(final Bindings bindings, final Class<T> clazz, final Type... genericTypes);

    /**
     * Method returns processed {@linkplain InstanceContext}. Instance of the class was created and
     * method {@linkplain InstanceContext#stage()} returns {@linkplain org.genthz.context.Stage#COMPLETE} after
     * this method had completed.
     *
     * @param context context for processing.
     * @param <T>     type of instance which will be created.
     * @return filled context.
     */
    public <T> InstanceContext<T> process(InstanceContext<T> context);

    /**
     * Method returns {@linkplain GenerationProvider}.
     *
     * @return generation provider.
     */
    public GenerationProvider generationProvider();
}
