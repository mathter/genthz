package org.genthz.dsl;

public interface Pathable {
    /**
     * The method is short alias for {@linkplain #path(String)}.
     */
    default public <T extends Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> T p(String path) {
        return this.path(path);
    }

    public <S extends Selector & Pathable & Strictable & Unstricable & Customable & InstanceBuilderFirst & FillerFirst> S path(String path);
}
