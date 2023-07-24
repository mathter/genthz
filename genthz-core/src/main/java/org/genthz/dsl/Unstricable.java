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

import java.lang.reflect.Type;

/**
 * This class contains methods that make it possible to create context selectors based on the type of object
 * being created (i.e. {@linkplain InstanceContext#type()}).
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Unstricable {
    /**
     * The method is short alias for {@linkplain #unstrict(Type, Type...)}.
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S us(Type type, Type... genericTypeArgs) {
        return this.unstrict(type, genericTypeArgs);
    }

    /**
     * The method is short alias for {@linkplain #unstrict(Class, Type...)}.
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S us(Class<T> type, Type... genericTypeArgs) {
        return this.unstrict(type, genericTypeArgs);
    }

    /**
     * Method returns object for building selector chain with class check element.
     *
     * <pre>
     *      Dsl dsl = new DashaDsl()
     *          .defs()                                     // create default rules for object creation.
     *          .unstrict(String.class)                     // matche only String.class
     *          .simple(ctx -&gt; "This is a test string");    // create instance builder with fixed generated value: "This is a test string"
     * </pre>
     *
     * @param type            typeof the path element.
     * @param genericTypeArgs types of type parameters array.
     * @param <T>             type of the path element.
     * @param <S>             type of the selector builder.
     * @return selector builder.
     */
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S unstrict(Type type, Type... genericTypeArgs);

    /**
     * Like {@linkplain #unstrict(Type, Type...)} but first parameter has {@linkplain Class} type.
     *
     * @param clazz           class of the path element.
     * @param genericTypeArgs types of type parameters array.
     * @param <T>             type of the path element.
     * @param <S>             type of the selector builder.
     * @return selector builder
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S unstrict(Class<T> clazz, Type... genericTypeArgs) {
        final S result;

        if (genericTypeArgs == null || genericTypeArgs.length == 0) {
            result = this.unstrict((Type) clazz, genericTypeArgs);
        } else {
            result = this.unstrict(TypeUtils.parameterize(clazz, genericTypeArgs));
        }

        return result;
    }
}
