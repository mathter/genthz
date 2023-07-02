package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.etalon.EtalonObjectFactoryTest;

public class EtalonDashaObectFactoryTest extends EtalonObjectFactoryTest {
    @Override
    public ObjectFactory objectFactory(GenerationProvider generationProvider) {
        return new DashaObjectFactory(generationProvider);
    }
}
