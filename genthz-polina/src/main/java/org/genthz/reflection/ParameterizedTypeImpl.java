package org.genthz.reflection;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Class<?> rawType;
    private final Type ownerType;

    private ParameterizedTypeImpl(Class<?> rawType,
                          Type[] actualTypeArguments,
                          Type ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
        this.ownerType = (ownerType != null) ? ownerType : rawType.getDeclaringClass();
        validateConstructorArguments();
    }

    private void validateConstructorArguments() {
        TypeVariable<?>[] formals = rawType.getTypeParameters();
        // check correct arity of actual type args
        if (formals.length != actualTypeArguments.length) {
            throw new RuntimeException(String.format("Mismatch of count of " +
                            "formal and actual type " +
                            "arguments in constructor " +
                            "of %s: %d formal argument(s) " +
                            "%d actual argument(s)",
                    rawType.getName(),
                    formals.length,
                    actualTypeArguments.length));
        }
        for (int i = 0; i < actualTypeArguments.length; i++) {
            // check actuals against formals' bounds
        }
    }

    public static ParameterizedTypeImpl make(Class<?> rawType,
                                             Type[] actualTypeArguments,
                                             Type ownerType) {
        return new ParameterizedTypeImpl(rawType, actualTypeArguments,
                ownerType);
    }

    public Type[] getActualTypeArguments() {
        return actualTypeArguments.clone();
    }

    public Class<?> getRawType() {
        return rawType;
    }

    public Type getOwnerType() {
        return ownerType;
    }

    /*
     * From the JavaDoc for java.lang.reflect.ParameterizedType
     * "Instances of classes that implement this interface must
     * implement an equals() method that equates any two instances
     * that share the same generic type declaration and have equal
     * type parameters."
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ParameterizedType) {
            // Check that information is equivalent
            ParameterizedType that = (ParameterizedType) o;

            if (this == that)
                return true;

            Type thatOwner = that.getOwnerType();
            Type thatRawType = that.getRawType();

            if (false) { // Debugging
                boolean ownerEquality = (ownerType == null ?
                        thatOwner == null :
                        ownerType.equals(thatOwner));
                boolean rawEquality = (rawType == null ?
                        thatRawType == null :
                        rawType.equals(thatRawType));

                boolean typeArgEquality = Arrays.equals(actualTypeArguments, // avoid clone
                        that.getActualTypeArguments());
                for (Type t : actualTypeArguments) {
                    System.out.printf("\t\t%s%s%n", t, t.getClass());
                }

                System.out.printf("\towner %s\traw %s\ttypeArg %s%n",
                        ownerEquality, rawEquality, typeArgEquality);
                return ownerEquality && rawEquality && typeArgEquality;
            }

            return
                    Objects.equals(ownerType, thatOwner) &&
                            Objects.equals(rawType, thatRawType) &&
                            Arrays.equals(actualTypeArguments, // avoid clone
                                    that.getActualTypeArguments());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return
                Arrays.hashCode(actualTypeArguments) ^
                        Objects.hashCode(ownerType) ^
                        Objects.hashCode(rawType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (ownerType != null) {
            sb.append(ownerType.getTypeName());

            sb.append("$");

            if (ownerType instanceof ParameterizedTypeImpl) {
                // Find simple name of nested type by removing the
                // shared prefix with owner.
                sb.append(rawType.getName().replace(((ParameterizedType) ownerType).getRawType().getTypeName() + "$",
                        ""));
            } else
                sb.append(rawType.getSimpleName());
        } else
            sb.append(rawType.getName());

        if (actualTypeArguments != null) {
            StringJoiner sj = new StringJoiner(", ", "<", ">");
            sj.setEmptyValue("");
            for (Type t : actualTypeArguments) {
                sj.add(t.getTypeName());
            }
            sb.append(sj.toString());
        }

        return sb.toString();
    }
}
