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
package org.genthz.dasha.context;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.context.Accessor;
import org.genthz.context.AccessorResolver;
import org.genthz.context.InstanceContext;
import org.genthz.context.Node;
import org.genthz.context.Typeable;
import org.genthz.function.FieldMatcher;
import org.genthz.reflection.GenericUtil;
import org.genthz.reflection.Util;
import org.genthz.util.StreamUtil;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DashaAccessorResolver implements AccessorResolver {
    private final GenericUtil genericUtil = new GenericUtil(false);

    private final Collection<FieldMatcher> includes;

    private final Collection<FieldMatcher> excludes;

    public DashaAccessorResolver() {
        this(null, null);
    }

    public DashaAccessorResolver(Collection<FieldMatcher> includes, Collection<FieldMatcher> excludes) {
        if (includes == null || includes.isEmpty() || excludes == null || excludes.isEmpty()) {
            this.includes = includes;
            this.excludes = excludes;
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "Only one parameter 'includes can be not empty! Now: includes: %s, excludes: %s",
                            includes,
                            excludes
                    )
            );
        }
    }

    @Override
    public <A extends Accessor & Node<String> & Typeable> Collection<A> resolve(InstanceContext up) {
        final Collection<A> result;
        final Type upType = up.type();
        final Map<TypeVariable<?>, Type> variableTypeMap = this.genericUtil.getActualTypeArguments(upType);

        result = Optional.of(upType)
                .map(e -> (Class) TypeUtils.getRawType(e, Object.class))
                .map(e -> StreamUtil.of(e, Class::getSuperclass))
                .orElse(Stream.empty())
                .flatMap(e -> Stream.of(e.getDeclaredFields()))
                .filter(e -> this.matche(FieldMatcher.of(e, variableTypeMap)))
                .filter(e -> Optional.of(e.getModifiers()).map(m -> !Modifier.isFinal(m) && !Modifier.isStatic(m)).get())
                .map(e -> (A) new FieldInstanceAccessor(up, e, Util.unrollType(variableTypeMap, e.getType())))
                .collect(Collectors.toList());

        return result;
    }

    private boolean matche(FieldMatcher.Context context) {
        boolean result;

        if (this.includes == null) {
            result = true;

            if (this.excludes != null) {
                for (FieldMatcher matcher : this.excludes) {
                    if (!(result &= matcher.nonMatche(context))) {
                        break;
                    }
                }
            }
        } else {
            result = false;

            for (FieldMatcher matcher : this.includes) {
                if (result |= matcher.matche(context)) {
                    break;
                }
            }
        }

        return result;
    }
}
