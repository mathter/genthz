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
package org.genthz.dsl;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.InstanceContext;

import java.util.function.Predicate;

/**
 * This class contains methods that make it possible to create context selectors based on different conditions.
 *
 * @author mathter
 * @since 3.0.0
 */
public final class Customs {
    private Customs() {
    }

    /**
     * The method returns a {@linkplain Predicate} that verifies that the class represents an array.
     *
     * @param <T> type represented by InstantContext.
     * @return predicate.
     */
    public static <T> Predicate<InstanceContext<T>> isArray() {
        return ctx -> ctx instanceof InstanceContext
                && TypeUtils.isArrayType(ctx.type());
    }
}
