package org.genthz.loly;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.loly.test.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathTest {
    @Test
    public void test() {
        ObjectFactory factory = ObjectFactoryProducer.producer().factory(new DefaultConfiguration() {
            {
                reg(path("name").instanceBuilder(c -> "Ho").simple());
            }
        });

        Person p = factory.build(Person.class);
        Assertions.assertEquals("Ho", p.getName());
    }
}
