package org.genthz.dsl;

public interface Metric<T extends Metric<T>> extends Comparable<Metric> {
    public int metric();

    public T metric(int mertic);
}
