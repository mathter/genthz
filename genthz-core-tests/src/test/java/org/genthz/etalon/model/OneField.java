package org.genthz.etalon.model;

import java.util.Objects;
import java.util.StringJoiner;

public class OneField {
    private String field;

    public OneField() {
    }

    public OneField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneField oneField = (OneField) o;
        return Objects.equals(field, oneField.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OneField.class.getSimpleName() + "[", "]")
                .add("field='" + field + "'")
                .toString();
    }
}
