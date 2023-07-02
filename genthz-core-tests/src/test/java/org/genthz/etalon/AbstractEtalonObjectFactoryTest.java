package org.genthz.etalon;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.dsl.Dsl;

public abstract class AbstractEtalonObjectFactoryTest {
    protected ObjectFactory objectFactory() {
        return this.objectFactory((GenerationProvider) null);
    }

    protected ObjectFactory objectFactory(Dsl dsl) {
        return this.objectFactory(dsl.build());
    }

    protected abstract ObjectFactory objectFactory(GenerationProvider generationProvider);
}
