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
package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.etalon.EtalonObjectFactoryTest;

public class EtalonDashaObectFactoryTest extends EtalonObjectFactoryTest {
    @Override
    public ObjectFactory objectFactory(GenerationProvider generationProvider) {
        return new DashaObjectFactory(generationProvider);
    }
}
