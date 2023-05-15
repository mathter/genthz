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

import org.genthz.context.InstanceContext;
import org.genthz.function.Filler;
import org.genthz.function.InstanceBuilder;

import java.util.Optional;

/**
 * This class contains information for class instance generation such as
 * {@linkplain org.genthz.function.InstanceBuilder}, {@linkplain InstanceBuilder}
 * {@linkplain Filler} and {@linkplain org.genthz.function.Selector}.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface GenerationProvider {
    /**
     * Method returns appropriate {@linkplain InstanceBuilder} for given context.
     * Implementation of this method must search instance builder in this provider.
     * If instance builder is not found the search must be continued in parent provider.
     *
     * @param context context.
     * @param <T>     type of instance.
     * @return instance builder for instance creation.
     * @see #up()
     */
    public <T> InstanceBuilder<T> instanceBuilder(InstanceContext context);

    /**
     * Method returns appropriate {@linkplain Filler} for given context.
     * Implementation of this method must search filler in this provider.
     * If filler is not found the search must be continued in parent provider.
     *
     * @param context context.
     * @param <T>     type of instance.
     * @return filler for instance filling.
     * @see #up()
     */
    public <T> Filler<T> filler(InstanceContext context);

    /**
     * Method returns {@linkplain Defaults} used in this {@linkplain GenerationProvider}.
     *
     * @return default information needed for generation of instance.
     */
    public Defaults defaults();

    /**
     * Method returns parent {@linkplain GenerationProvider}. Can be empty.
     *
     * @return parent generation provider.
     */
    public Optional<GenerationProvider> up();
}
