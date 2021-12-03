/*
 * GenThz - testing becomes easier
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
package org.genthz.configuration.dsl.polina;

import org.genthz.configuration.dsl.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class Antrl4PathProcessorTest {
    private static class Provider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of("/", PathRootSelector.class),
                    Arguments.of("one/two/three", PathStaticElementSelector.class),
                    Arguments.of("..2/../../..4", PathSkipElementSelector.class),
                    Arguments.of("one*", PathMatchedElementSelector.class)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(Provider.class)
    public void test(String path, Class<Selector<?>> selectorClass) {
        Selector<Object> selector = new Antrl4PathProcessor().process(null, path);

        Assertions.assertNotNull(selector);
        Assertions.assertTrue(selectorClass.isAssignableFrom(selector.getClass()));
    }
}
