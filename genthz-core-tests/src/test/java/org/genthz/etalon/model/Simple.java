package org.genthz.etalon.model;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class Simple {
    private String stringField;

    private Date dateField;

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public Simple() {
    }

    public Simple(String stringField, Date dateField) {
        this.stringField = stringField;
        this.dateField = dateField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simple simple = (Simple) o;
        return Objects.equals(stringField, simple.stringField) && Objects.equals(dateField, simple.dateField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringField, dateField);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Simple.class.getSimpleName() + "[", "]")
                .add("stringField='" + stringField + "'")
                .add("dateField=" + dateField)
                .toString();
    }
}
