package org.genthz.dsl;

import org.genthz.Defaults;
import org.genthz.GenerationProvider;

public interface Dsl extends Pathable, Strictable, Unstricable, Customable {

    public Dsl defaults(Defaults defaults);

    default public GenerationProvider build() {
        return this.build(null);
    }

    public GenerationProvider build(GenerationProvider parent);
}
