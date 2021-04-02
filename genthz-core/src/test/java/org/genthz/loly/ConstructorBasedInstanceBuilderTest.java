package org.genthz.loly;

import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.configuration.dsl.InstanceBuilders;
import org.genthz.loly.test.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.UUID;

public class ConstructorBasedInstanceBuilderTest {

    @Test
    public void testWithoutParamters() {
        final Configuration configuration = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class)
                                .byConstructor(InstanceBuilders.byDefaultConstructor())
                                .simple()
                );
            }
        };
        final ObjectFactory factory = ObjectFactoryProducer.producer().factory(configuration);

        Person value = factory.build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNull(value.getBirthDate());
        Assertions.assertNull(value.getUuid());
        Assertions.assertNull(value.getLastName());
        Assertions.assertNull(value.getName());
    }

    @Test
    public void testWithParamters() {
        final Configuration configuration = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class)
                                .byConstructor(InstanceBuilders.byArgumentTypes(new Class[]{UUID.class, String.class, String.class, Date.class}))
                                .simple()
                );
            }
        };
        final ObjectFactory factory = ObjectFactoryProducer.producer().factory(configuration);

        Person value = factory.build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getBirthDate());
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getName());
    }

    @Test
    public void testWithParamterCount() {
        final Configuration configuration = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class)
                                .byConstructor(InstanceBuilders.byArgumentCount(4))
                                .simple()
                );
            }
        };
        final ObjectFactory factory = ObjectFactoryProducer.producer().factory(configuration);

        Person value = factory.build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getBirthDate());
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getName());
    }

    @Test
    public void testByConstructor() throws Exception {
        final Constructor<Person> constructor = Person.class.getConstructor(
                new Class[]{UUID.class,
                        String.class,
                        String.class,
                        Date.class});

        final Configuration configuration = new DefaultConfiguration() {
            {
                reg(
                        strict(Person.class).byConstructor(InstanceBuilders.byConstructor(constructor)).simple()
                );
            }
        };

        final ObjectFactory factory = ObjectFactoryProducer.producer().factory(configuration);

        Person value = factory.build(Person.class);

        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getBirthDate());
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getName());
    }
}
