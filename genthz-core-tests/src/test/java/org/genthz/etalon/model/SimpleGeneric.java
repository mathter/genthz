package org.genthz.etalon.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleGeneric<K, T>extends Simple {
    private T tField;

    private Collection<T> collectionField;

    private List<T> listField;

    private Set<T> setField;

    private Map<K, T> mapField;

    private T[] arrayField;

    public T gettField() {
        return tField;
    }

    public void settField(T tField) {
        this.tField = tField;
    }

    public Collection<T> getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(Collection<T> collectionField) {
        this.collectionField = collectionField;
    }

    public List<T> getListField() {
        return listField;
    }

    public void setListField(List<T> listField) {
        this.listField = listField;
    }

    public Set<T> getSetField() {
        return setField;
    }

    public void setSetField(Set<T> setField) {
        this.setField = setField;
    }

    public Map<K, T> getMapField() {
        return mapField;
    }

    public void setMapField(Map<K, T> mapField) {
        this.mapField = mapField;
    }

    public T[] getArrayField() {
        return arrayField;
    }

    public void setArrayField(T[] arrayField) {
        this.arrayField = arrayField;
    }
}