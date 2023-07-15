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

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.etalon.model.SimpleGeneric2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class EtalonConstructorObjectFactoryTest extends AbstractEtalonObjectFactoryTest {
    @ParameterizedTest
    @DisplayName("Test of constructor argument based generation")
    @MethodSource("data")
    public void test(Type t1, Type t2) {
        final SimpleGeneric2<?, ?> value = this.objectFactory().get(SimpleGeneric2.class, t1, t2);

        System.out.println(value);
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(String.class, String.class),
                Arguments.of(TypeUtils.parameterize(List.class, String.class), String.class),
                Arguments.of(TypeUtils.parameterize(Map.class, String.class, int.class), String.class)
        );
    }
}
