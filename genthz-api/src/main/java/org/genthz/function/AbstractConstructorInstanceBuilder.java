/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2021 mathter@mail.ru
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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.genthz.NewInstanceException;
import org.genthz.ObjectFactory;
import org.genthz.context.def.ConstructorIndexedContextImpl;
import org.genthz.context.Context;
import org.genthz.util.Constants;
import org.genthz.util.Util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractConstructorInstanceBuilder<T> implements InstanceBuilder<T> {

    protected abstract Constructor<T> getConstructor(Context<T> context);

    @Override
    public T apply(Context<T> context) {
        return Optional.of(context)
                .map(e -> build(this.getConstructor(e), e))
                .get();
    }

    private T build(Constructor<T> constructor, Context<T> context) {
        final T result;

        try {
            result = Util.newInstance(constructor, this.buildParams(constructor, context));
        } catch (Exception e) {
            throw new NewInstanceException(context, "Can't create new incetance!", e);
        }

        return result;
    }

    private Object[] buildParams(Constructor<T> constructor, Context<T> context) {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final List<Object> parameters = new ArrayList<>(parameterTypes.length);

        for (int i = 0; i < parameterTypes.length; i++) {
            final Class<?> parameterType = parameterTypes[i];
            parameters.add(this.buildParameter(context, parameterType, i));
        }

        return parameters.toArray();
    }

    private <P, T> T buildParameter(Context<P> parent, Class<T> clazz, int index) {
        final ObjectFactory objectFactory = parent.objectFactory();
        final Context<T> parameterContext = new ConstructorIndexedContextImpl<>(objectFactory, parent, index, clazz);

        return objectFactory.build(parameterContext).node();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
    }
}
