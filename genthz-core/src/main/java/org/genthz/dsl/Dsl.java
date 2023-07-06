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
package org.genthz.dsl;

import org.genthz.Defaults;
import org.genthz.GenerationProvider;

/**
 * This root class for building {@linkplain GenerationProvider}.
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Dsl extends Pathable, Strictable, Unstricable, Customable {
    public static final int DEFAULT_USER_DEFINED_METRIC = 0;

    /**
     * Method sets {@linkplain Defaults} for newly created generation provider.
     *
     * @param defaults default information.
     * @return this object.
     */
    public Dsl defaults(Defaults defaults);

    /**
     * Method returns {@linkplain Defaults}.
     *
     * @return defaults.
     */
    public Defaults defaults();

    /**
     * Method build new generation provider.
     *
     * @return generation provider.
     */
    default public GenerationProvider build() {
        return this.build(null);
    }

    /**
     * Method build new generation provider with parent.
     *
     * @param parent parent generation provider.
     * @return generation provider.
     */
    public GenerationProvider build(GenerationProvider parent);
}
