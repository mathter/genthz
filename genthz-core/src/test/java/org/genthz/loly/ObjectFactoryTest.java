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
package org.genthz.loly;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.configuration.dsl.DslFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectFactoryTest {

    @Test
    public void createObjectFactory() {
        final ObjectFactoryProducer producer = ObjectFactoryProducer.producer(ObjectFactoryProducer.DEFAULT_FACTORY);
        Assertions.assertNotNull(producer);

        final ObjectFactory objectFactory = producer.factory(new DefaultConfiguration(DslFactory.dsl()));
        Assertions.assertNotNull(objectFactory);
    }
}
