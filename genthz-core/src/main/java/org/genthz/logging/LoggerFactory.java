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
package org.genthz.logging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

public abstract class LoggerFactory {
    protected abstract Logger instance();

    public static final Logger get() {
        final Logger result;
        final Collection<LoggerFactory> factories = new ArrayList<>();

        for (LoggerFactory factory : ServiceLoader.load(LoggerFactory.class)) {
            factories.add(factory);
        }

        if (factories.size() == 0) {
            System.out.println(String.format(
                    "There is no %s in the class path! NoLogger will be used.",
                    LoggerFactory.class
            ));

            result = new NoLoggerFactory().instance();
        } else if (factories.size() > 1) {
            System.out.println(String.format(
                    "There are more then one %s implementations found in the class path!",
                    LoggerFactory.class
            ));

            result = factories.stream().findFirst().get().instance();
        } else {
            result = factories.stream().findFirst().get().instance();
        }

        return result;
    }
}
