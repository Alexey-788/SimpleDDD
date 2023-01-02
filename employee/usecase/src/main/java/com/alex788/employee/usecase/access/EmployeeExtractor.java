package com.alex788.employee.usecase.access;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.domain.EmployeePassportId;

import java.util.Optional;

public interface EmployeeExtractor {

    Optional<Employee> getByPassportId(EmployeePassportId passportId);
}
