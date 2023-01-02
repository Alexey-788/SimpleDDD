package com.alex788.employee.usecase.access;

import com.alex788.ddd.employee.domain.Employee;

public interface EmployeePersister {

    void save(Employee employee);
}
