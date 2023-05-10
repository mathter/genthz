package org.genthz.dsl;

import org.genthz.GenerationProvider;

public interface Dsl extends Pathable, Strictable, Unstricable, Customable {
    default public GenerationProvider build() {
        return this.build(null);
    }

    public GenerationProvider build(GenerationProvider parent);
}
