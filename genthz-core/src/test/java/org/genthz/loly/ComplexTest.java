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
import java.util.Collection;
import java.util.function.Supplier;

public class ComplexTest {
    private static String MANAGER_NAME_0;

    private static String MANAGER_NAME_1;

    private static Configuration CONF;

    @BeforeAll
    public static void init() {
        MANAGER_NAME_0 = RandomStringUtils.randomAlphanumeric(10);
        MANAGER_NAME_1 = RandomStringUtils.randomAlphanumeric(10);
        CONF = new DefaultConfiguration() {
            {
                reg(
                        strict(Manager.class)
                                .defaultFiller()
                                .custom((Filler<Manager>) (managerContext, manager) -> {
                                    manager.setEmployees(new ArrayList<>());
                                    manager.setName(MANAGER_NAME_1);
                                    return manager;
                                }),
                        /**
                         * {@linkplain Employee} generator.
                         */
                        strict(Employee.class)
                                .defaultFiller()
                                .excluding("manager")
                                .custom((Filler<Employee>) (employeeContext, employee) -> {
                                    if (employeeContext.parent() != null && employeeContext.parent().clazz().equals(Manager.class)) {
                                        employee.getManager().getEmployees().add(employee);
                                    }

                                    return employee;
                                })
                        /**
                         * Manager generator for {@linkplain Employee#manager}.
                         */
//                        strict(Employee.class)
//                                .path("..")
//                                .strict(Manager.class)
//                                .defaultFiller()
//                                .excluding("employees")
//                                .custom((Filler<Manager>) (managerContext, manager) -> {
//                                    manager.setEmployees(new ArrayList<>());
//                                    manager.setName(MANAGER_NAME_0);
//                                    return manager;
//                                })
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
        Assertions.assertEquals(MANAGER_NAME_0, value.getManager().getName());
        Assertions.assertNotNull(value.getManager().getLastName());
        Assertions.assertNotNull(value.getManager().getBirthDate());
        Assertions.assertNotNull(value.getManager().getEmployees());
        Assertions.assertEquals(1, value.getManager().getEmployees().size());
    }

    @Test
    public void testManager() {
        final ObjectFactory factory = ObjectFactoryProducer
                .producer()
                .factory(CONF);

        Manager value = factory.build(Manager.class);
        Assertions.assertNotNull(value);
        Assertions.assertNotNull(value.getUuid());
        Assertions.assertNotNull(value.getName());
        Assertions.assertEquals(MANAGER_NAME_1, value.getName());
        Assertions.assertNotNull(value.getLastName());
        Assertions.assertNotNull(value.getBirthDate());
    }
}
