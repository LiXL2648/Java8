package com.li.java8.interfact.impl;

import com.li.java8.domain.Employee;
import com.li.java8.interfact.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() < 18;
    }
}
