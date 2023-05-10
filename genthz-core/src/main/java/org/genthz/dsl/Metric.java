package org.genthz.dsl;

public interface Metric<T extends Metric<T>> extends Comparable<Metric<T>> {

    default public int m() {
        return this.metric();
    }

    public int metric();

    default public T m(int metric) {
        return this.metric(metric);
    }

    public T metric(int mertic);
}
