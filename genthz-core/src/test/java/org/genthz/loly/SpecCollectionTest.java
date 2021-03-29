package org.genthz.loly;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.Spec;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.loly.test.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class SpecCollectionTest {

    @Test
    public void test() {
        final ObjectFactory factory = ObjectFactoryProducer.producer().factory(new DefaultConfiguration() {
            {
                reg(
                        path("/").nonstrict(Collection.class).collectionFiller(Person.class, 20)
                );
            }
        });

        Collection<Person> c = factory.build(Spec.of(Collection.class)).get();

        Assertions.assertNotNull(c);
        Assertions.assertEquals(20, c.size());

        for (Person person : c) {
            Assertions.assertNotNull(person);
            Assertions.assertEquals(Person.class, person.getClass());

            Assertions.assertNotNull(person.getUuid());
            Assertions.assertNotNull(person.getName());
            Assertions.assertNotNull(person.getLastName());
            Assertions.assertNotNull(person.getBirthDate());
        }
    }
}
