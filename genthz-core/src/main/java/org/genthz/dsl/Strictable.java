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

import org.genthz.context.InstanceContext;

import java.lang.reflect.Type;

/**
 * This class contains methods that make it possible to create context selectors based on the type of object
 * being created (i.e. {@linkplain InstanceContext#type()}).
 *
 * @author mathter
 * @since 3.0.0
 */
public interface Strictable {
    /**
     * The method is short alias for {@linkplain #strict(Type, Type...)} )}.
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S s(Type type, Type... genericTypeArgs) {
        return this.strict(type, genericTypeArgs);
    }

    /**
     * Method returns object for building selector chain with class check element.
     * The selector that will be created uses the {@linkplain Class#equals(Object)} method to test the context.
     *
     * <pre>
     *      Dsl dsl = new DashaDsl()
     *          .defs()                                     // create default rules for object creation.
     *          .strict(String.class)                       // matche only String.class
     *          .simple(ctx -> "This is a test string");    // create instance builder with fixed generated value: "This is a test string"
     * </pre>
     *
     * @param type            type of the path element.
     * @param genericTypeArgs types of type parameters array.
     * @param <T>             type of the path element.
     * @param <S>             type of the selector builder.
     * @return selector buider.
     */
    public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S strict(Type type, Type... genericTypeArgs);

    /**
     * Like {@linkplain #strict(Type, Type...)} but first parameter has {@linkplain Class} type.
     *
     * @param clazz           class of the path element.
     * @param genericTypeArgs types of type parameters array.
     * @param <T>             type of the path element.
     * @param <S>             type of the selector builder.
     * @return selector builder.
     */
    default public <T, S extends Pathable & Customable & InstanceBuilderFirst<T> & FillerFirst<T> & Metric<S> & Using<S>> S strict(Class<T> clazz, Type... genericTypeArgs) {
        return this.strict((Type) clazz, genericTypeArgs);
    }
}
