package com.alex788.ddd.employee.usecase.scenario;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.usecase.GetAllEmployees;
import com.alex788.ddd.employee.usecase.access.EmployeeExtractor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAllEmployeesUseCase implements GetAllEmployees {

    private final EmployeeExtractor extractor;

    @Override
    public List<EmployeeInfo> execute() {
        List<Employee> employees = extractor.getAll();
        return employees.stream()
                .map(employee -> new EmployeeInfo(employee.getPassportId(),
                        employee.getName(),
                        employee.getDepartment(),
                        employee.getPosition()))
                .collect(Collectors.toList());
    }
}
