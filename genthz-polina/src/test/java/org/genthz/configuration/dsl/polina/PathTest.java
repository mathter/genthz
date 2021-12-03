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

import org.genthz.ObjectFactory;
import org.genthz.configuration.dsl.Selector;
import org.genthz.context.context.Bindings;
import org.genthz.context.context.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

public class PathTest {

    @ParameterizedTest
    @CsvSource(value = {
            "/;/",
            "/one/two/three;/one/two/three",
            "two/three;/one/two/three",
            "../three;/one/two/three",
            "..2/three;/one/two/three",
            "/..2/three;/one/two/three",
            "th*;/one/two/three"
    }, delimiter = ';')
    public void test(String selectorPath, String contextPath) {
        final Selector<Object> selector = new Antrl4PathProcessor().process(null, selectorPath);
        final Context<?> context = this.buildContext(contextPath);

        Assertions.assertTrue(selector.test(context));
    }

    private Context<?> buildContext(String path) {
        final Context<?> result;

        result = Arrays.stream(path.split("/"))
                .sequential()
                .filter(e -> !e.isEmpty())
                .reduce(
                        path.startsWith("/") ? new TestContext(null, "/") : null,
                        (l, r) -> new TestContext(l, r),
                        (l, r) -> null
                );

        return result;
    }

    private class TestContext implements Context<Void> {
        private final Context<?> parent;

        private final String name;

        public TestContext(Context<?> parent, String name) {
            this.parent = parent;
            this.name = name;
        }

        @Override
        public Bindings bindings() {
            return null;
        }

        @Override
        public Class<Void> clazz() {
            return Void.class;
        }

        @Override
        public Void value() {
            return null;
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public Context<?> parent() {
            return this.parent;
        }

        @Override
        public ObjectFactory objectFactory() {
            return null;
        }
    }
}
