package org.genthz.dsl;

public interface Pathable {
    /**
     * The method is short alias for {@linkplain #path(String)}.
     */
    default public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S p(String path) {
        return this.path(path);
    }

    public <S extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst & Metric<S> & Using<S>> S path(String path);
}
