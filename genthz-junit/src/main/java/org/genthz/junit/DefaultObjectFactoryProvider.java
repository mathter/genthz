package org.genthz.junit;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProvider;
import org.genthz.dasha.DashaObjectFactory;

public class DefaultObjectFactoryProvider implements ObjectFactoryProvider {
    @Override
    public ObjectFactory objectFactory() {
        return new DashaObjectFactory();
    }
}
