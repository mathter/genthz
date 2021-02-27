/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter
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
package org.genthz.util;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class NameGenerator {
    private static final String NAME = "predefined.name.";

    private static final NameGenerator SINGLITON = new NameGenerator();

    private long index = 0L;

    private final String[] predefinedNames;

    private final Random nameIndexSelector;

    private final Lock lock = new ReentrantLock();

    private NameGenerator() {
        this.predefinedNames = NameGenerator.predefinedNames();
        this.nameIndexSelector = new Random();
    }

    public static final String nextName() {
        return SINGLITON.next();
    }

    private String next() {
        this.lock.lock();

        try {
            return new StringBuilder(this.predefinedNames[this.nameIndexSelector.nextInt(this.predefinedNames.length)])
                    .append('-')
                    .append(this.index++)
                    .toString();
        } finally {
            this.lock.unlock();
        }
    }

    private static String[] predefinedNames() {
        String value;
        final ResourceBundle bundle = ResourceBundle.getBundle("org.genthz.configuration.dsl.loly.messages");
        final List<String> result = new ArrayList<>();

        for (int i = 0; true; i++) {
            try {
                value = bundle.getString(NAME + i);
                result.add(value);
            } catch (MissingResourceException e) {
                break;
            }
        }

        return result.toArray(new String[result.size()]);
    }
}
