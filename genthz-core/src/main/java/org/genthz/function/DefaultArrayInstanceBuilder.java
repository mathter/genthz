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
package org.genthz.function;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.Defaults;
import org.genthz.ObjectFactory;
import org.genthz.context.InstanceContext;
import org.genthz.reflection.GenericUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

public class DefaultArrayInstanceBuilder<T> implements InstanceBuilder<T> {
    private final GenericUtil genericUtil;

    public DefaultArrayInstanceBuilder() {
        this(new GenericUtil(false));
    }

    public DefaultArrayInstanceBuilder(GenericUtil genericUtil) {
        this.genericUtil = genericUtil;
    }

    @Override
    public T instance(InstanceContext<T> context) {
        final T instance;
        final ObjectFactory objectFactory = context.objectFactory();
        final Type type = context.type();

        if (TypeUtils.isArrayType(type)) {
            final Defaults defaults = objectFactory.generationProvider().defaults();
            instance = (T) Array.newInstance(
                    this.genericUtil.getRawClass(null, TypeUtils.getArrayComponentType(type)),
                    defaults.defaultCollectionSize()
            );
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "Context type: %s is not an array!",
                            type
                    )
            );
        }

        return instance;
    }
}
