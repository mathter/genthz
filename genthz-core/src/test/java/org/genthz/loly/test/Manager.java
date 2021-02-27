package org.genthz.loly.test;

import java.util.Collection;

public class Manager extends Person {
    private Collection<Employee> employees;

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
