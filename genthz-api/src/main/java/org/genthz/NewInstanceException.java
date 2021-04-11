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

public class NewInstanceException extends GeneratedException {
    private final Class<?> clazz;

    public NewInstanceException(Context<?> context, Throwable cause) {
        super((buildMessage(context,null)), cause);
        this.clazz = context.clazz();
    }

    public NewInstanceException(Context<?> context, String message, Throwable cause) {
        super((buildMessage(context, message)), cause);
        this.clazz = context.clazz();
    }

    public NewInstanceException(Context<?> context, String message) {
        super((buildMessage(context, message)));
        this.clazz = context.clazz();
    }


    public NewInstanceException(Class<?> clazz, Throwable cause) {
        super(buildMessage(clazz, null), cause);
        this.clazz = clazz;
    }

    private static String buildMessage(Class<?> clazz, String message) {
        return new StringBuilder("Can't build new instance of class=")
                .append(clazz)
                .append('!')
                .append(message != null ? " " + message : "")
                .toString();
    }

    private static String buildMessage(Context<?> context, String message) {
        return new StringBuilder("Can't build new instance of class=")
                .append(context != null ? context.clazz() : null)
                .append(" for context=").append(context)
                .append(message != null ? message : "")
                .toString();
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
