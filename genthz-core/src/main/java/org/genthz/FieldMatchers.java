package org.genthz;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.genthz.function.FieldMatcher;
import org.genthz.reflection.reference.GetMethodReference;
import org.genthz.reflection.reference.ReferenceUtil;
import org.genthz.reflection.reference.SetMethodReference;

import java.beans.Introspector;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Stream;

public class FieldMatchers {
    public static <F extends FieldMatcher & D3T & T3D> F name(String fieldName) {
        return (F) new FieldMatcherImpl(null, null, fieldName);
    }

    public static <RR, R> FieldMatcher name(GetMethodReference<RR, R> reference) {
        return name(ReferenceUtil.method(reference));
    }

    public static <RR, R> FieldMatcher name(SetMethodReference<RR, R> reference) {
        return name(ReferenceUtil.method(reference));
    }

    private static <F extends FieldMatcher & D3T & T3D> F name(Method method) {
        final F result;
        final Class<?> declaring = method.getDeclaringClass();

        result = (F) new FieldMatcherImpl(null, null, ReferenceUtil.propertyName(method));

        return result;
    }

    public static <F extends FieldMatcher & N3T & T3N> F declaring(Type declaringType) {
        return (F) new FieldMatcherImpl(declaringType, null, null);
    }

    public static <F extends FieldMatcher & D3N & N3D> F type(Type fieldType) {
        return (F) new FieldMatcherImpl(null, fieldType, null);
    }

    public interface N3D {
        public <F extends FieldMatcher & D> F name(String fieldName);
    }

    public interface N3T {
        public <F extends FieldMatcher & T> F name(String fieldName);
    }

    public interface D3T {
        public <F extends FieldMatcher & T> F declaring(Type declaringType);
    }

    public interface D3N {
        public <F extends FieldMatcher & N> F declaring(Type declaringType);
    }

    public interface T3D {
        public <F extends FieldMatcher & D> F type(Type fieldType);
    }

    public interface T3N {
        public <F extends FieldMatcher & N> F type(Type fieldType);
    }

    public interface D {
        public FieldMatcher declaring(Type declaringType);
    }

    public interface T {
        public FieldMatcher type(Type fieldType);
    }

    public interface N {
        public FieldMatcher name(String fieldName);
    }

    private static class FieldMatcherImpl implements FieldMatcher, D3T, D3N, T3D, T3N, N3D, N3T, D, T, N {
        private Type declaringType;

        private Type type;

        private String name;

        public FieldMatcherImpl(Type declaringType, Type type, String name) {
            this.declaringType = declaringType;
            this.type = type;
            this.name = name;
        }

        @Override
        public boolean matche(Context context) {
            return context != null
                    && (this.name == null || this.name.equals(context.name()))
                    && (this.type == null || TypeUtils.isAssignable(context.type(), this.type))
                    && (this.declaringType == null || TypeUtils.isAssignable(context.declaring(), this.declaringType));
        }

        @Override
        public FieldMatcherImpl declaring(Type declaringType) {
            this.declaringType = declaringType;
            return this;
        }

        @Override
        public FieldMatcherImpl type(Type fieldType) {
            this.type = fieldType;
            return this;
        }

        @Override
        public FieldMatcher name(String fieldName) {
            this.name = fieldName;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("FieldMatcherImpl{");
            sb.append("declaringType=").append(declaringType);
            sb.append(", type=").append(type);
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
