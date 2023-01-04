package com.alex788.ddd.employee.usecase.invariant;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.domain.EmployeeAlreadyExists;
import com.alex788.ddd.employee.domain.EmployeePassportId;
import com.alex788.ddd.employee.usecase.access.EmployeeExtractor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeAlreadyExistsImpl implements EmployeeAlreadyExists {

    private final EmployeeExtractor extractor;

    @Override
    public boolean check(EmployeePassportId passportId) {
        Optional<Employee> employeeOpt = extractor.getByPassportId(passportId);
        return employeeOpt.isPresent();
    }
}
