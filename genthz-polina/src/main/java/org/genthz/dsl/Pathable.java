package org.genthz.dsl;

public interface Pathable {
    /**
     * The method is short alias for {@linkplain #path(String)}.
     */
    default public <T extends Pathable & Strictable & Unstricable & Customable & Functions> T p(String path) {
        return this.path(path);
    }

    public <S extends Selector & Pathable & Strictable & Unstricable & Customable & Functions> S path(String path);
}
