package org.genthz.loly.test;

import java.util.Collection;
import java.util.UUID;

public class Department {
    private UUID uuid;

    private Collection<Employee> employees;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
