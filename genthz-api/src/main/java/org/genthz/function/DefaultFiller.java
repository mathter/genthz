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
import org.genthz.ObjectFactory;
import org.genthz.context.def.AbstractContext;
import org.genthz.context.Accessor;
import org.genthz.context.Context;
import org.genthz.context.Stage;
import org.genthz.util.Constants;
import org.genthz.util.StreamUtil;
import org.genthz.util.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DefaultFiller<T> implements Filler<T> {
    private static final Predicate<Member> STATIC_PREDICATE = new SkipStaticFieldsAndMethodsAndConstructorsPredicate();

    private final Predicate<Member> predicate;

    public DefaultFiller() {
        this(STATIC_PREDICATE);
    }

    public DefaultFiller(Predicate<Member> memberPredicate) {
        this.predicate = memberPredicate != null ? STATIC_PREDICATE.and(memberPredicate) : STATIC_PREDICATE;
    }

    @Override
    public T apply(Context<T> context, T value) {
        this.accessors(context)
                .forEach(this::fill);

        return value;
    }

    private <T> void fill(Context<T> context) {
        if (Stage.COMPLETE != context.stage()) {
            context.objectFactory().build(context);
        }
    }

    private <P extends Context & Accessor> Stream<P> accessors(Context<T> context) {
        return Optional
                .of(context.clazz())
                .map(this::classes)
                .orElse(Stream.empty())
                .flatMap(this::fields)
                .filter(this.predicate)
                .map(e -> (P) new FieldAccessor(context.objectFactory(), context, e, context.node()));
    }

    private <T> Stream<Field> fields(Class<T> clazz) {
        return Stream.of(clazz.getDeclaredFields());
    }

    private static class SkipStaticFieldsAndMethodsAndConstructorsPredicate implements Predicate<Member> {
        @Override
        public boolean test(Member member) {
            return Optional
                    .of(member.getModifiers())
                    .map(e -> !(Modifier.isStatic(e) || Modifier.isFinal(e)))
                    .get();
        }
    }

    private Stream<Class<?>> classes(Class<?> clazz) {
        return StreamUtil.of(clazz, Class::getSuperclass);
    }

    private static class FieldAccessor<T> extends AbstractContext<T> {
        private final Field field;

        private final Object object;

        public FieldAccessor(ObjectFactory objectFactory, Context<?> parent, Field field, Object object) {
            super(objectFactory, parent);
            this.field = field;
            this.object = object;
        }

        @Override
        public T get() {
            return Util.getFieldValue(this.field, this.object);
        }

        @Override
        public void setInstance(T value) {
            super.setInstance(value);
            Util.setFieldValue(this.field, this.object, value);
        }

        @Override
        public void setFilled(T value) {
            super.setFilled(value);
            Util.setFieldValue(this.field, this.object, value);
        }

        @Override
        public Class<T> clazz() {
            return (Class<T>) this.field.getType();
        }

        @Override
        public T node() {
            return this.get();
        }

        @Override
        public String name() {
            return this.field.getName();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
    }
}