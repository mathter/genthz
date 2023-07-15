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
package org.genthz.etalon;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.dsl.Dsl;

public abstract class AbstractEtalonObjectFactoryTest {
    protected ObjectFactory objectFactory() {
        return this.objectFactory((GenerationProvider) null);
    }

    protected ObjectFactory objectFactory(Dsl dsl) {
        return this.objectFactory(dsl.build());
    }

    protected abstract ObjectFactory objectFactory(GenerationProvider generationProvider);
}
