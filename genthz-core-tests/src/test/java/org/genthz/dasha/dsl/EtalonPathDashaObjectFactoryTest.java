package org.genthz.dasha.dsl;

import org.genthz.GenerationProvider;
import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.etalon.EtalonPathObjectFactoryTest;

public class EtalonPathDashaObjectFactoryTest extends EtalonPathObjectFactoryTest {
    @Override
    public ObjectFactory objectFactory(GenerationProvider generationProvider) {
        return new DashaObjectFactory(generationProvider);
    }
}
