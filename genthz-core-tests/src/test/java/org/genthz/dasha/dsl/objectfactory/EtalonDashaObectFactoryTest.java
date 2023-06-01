package org.genthz.dasha.dsl.objectfactory;

import org.genthz.ObjectFactory;
import org.genthz.dasha.DashaObjectFactory;
import org.genthz.etalon.EtalonObjectFactoryTest;

public class EtalonDashaObectFactoryTest extends EtalonObjectFactoryTest {
    @Override
    public ObjectFactory objectFactory() {
        return new DashaObjectFactory();
    }
}
