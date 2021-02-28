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
package org.genthz;

public class ProducerNotFoundException extends GeneratedException {
    private final String id;

    public ProducerNotFoundException(String id) {
        super(message(id), null, false, false);
        this.id = id;
    }

    private static String message(String factoryName) {
        return String.format("There is no factory with name equals to '%s'!", factoryName != null ? factoryName : "<null>");
    }

    public String getId() {
        return id;
    }
}
