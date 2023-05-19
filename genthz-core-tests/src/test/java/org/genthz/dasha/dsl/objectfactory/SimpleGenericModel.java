package org.genthz.dasha.dsl.objectfactory;

import java.util.List;
import java.util.Set;

public class SimpleGenericModel<T, D> extends SimpleTestModel {
    private List<String> otherNames;

    private T value;

    private Set<D> values;

    private Set<List> groups;

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Set<D> getValues() {
        return values;
    }

    public void setValues(Set<D> values) {
        this.values = values;
    }

    public Set<List> getGroups() {
        return groups;
    }

    public void setGroups(Set<List> groups) {
        this.groups = groups;
    }
}
