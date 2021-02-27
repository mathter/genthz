package org.genthz.loly;

import org.apache.commons.lang3.RandomStringUtils;
import org.genthz.Filler;
import org.genthz.ObjectFactory;
import org.genthz.ObjectFactoryProducer;
import org.genthz.configuration.dsl.Configuration;
import org.genthz.configuration.dsl.DefaultConfiguration;
import org.genthz.loly.test.Employee;
import org.genthz.loly.test.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ComplexTest {
    private static String MANAGER_NAME;
    private static Configuration CONF;

    @BeforeAll
    public static void init() {
        MANAGER_NAME = RandomStringUtils.randomAlphanumeric(10);
        CONF = new DefaultConfiguration() {
            {
                reg(
                        strict(Manager.class)
                                .defaultFiller()
                                .custom((Filler<Manager>) (managerContext, manager) -> {
                                    return manager;
                                }),
                        /**
                         * {@linkplain Employee} generator for as Root object.
                         */
                        path("/")
                                .strict(Employee.class)
                                .defaultFiller()
                                .custom((Filler<Employee>) (employeeContext, employee) -> {
                                    employee.getManager().getEmployees().add(employee);
                                    return employee;
                                }),
                        /**
                         * Manager generator for {@linkplain Employee#manager}.
                         */
                        strict(Employee.class)
                                .path("..")
                                .strict(Manager.class)
                                .defaultFiller()
                                .excluding("employees")
                                .custom((Filler<Manager>) (managerContext, manager) -> {
                                    manager.setEmployees(new ArrayList<>());
                                    manager.setName(MANAGER_NAME);
                                    return manager;
                                })
                );
            }

            @Override
            public Supplier<Long> maxGenerationDeep() {
                return () -> 4L;
            }
        };
    }

    @Test
    public void testPerson() {
        final ObjectFactory factory = ObjectFactoryProducer
                .producer()
                .factory(CONF);

        Employee value = factory.build(Employee.class);
        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthDate());

        Assertions.assertNotNull(value.getManager());
        Assertions.assertNotNull(value.getManager().getUuid());
        Assertions.assertNotNull(value.getManager().getName());
        Assertions.assertEquals(MANAGER_NAME, value.getManager().getName());
        Assertions.assertNotNull(value.getManager().getLastName());
        Assertions.assertNotNull(value.getManager().getBirthDate());
        Assertions.assertNotNull(value.getManager().getEmployees());
        Assertions.assertEquals(1, value.getManager().getEmployees().size());
    }
}
