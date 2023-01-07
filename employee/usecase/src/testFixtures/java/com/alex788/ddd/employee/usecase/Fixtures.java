package com.alex788.ddd.employee.usecase;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.domain.EmployeeId;
import com.alex788.ddd.employee.domain.EmployeePassportId;
import com.alex788.ddd.employee.usecase.access.EmployeeExtractor;

import java.util.*;
import java.util.stream.Collectors;

public class Fixtures {

    public static EmployeeExtractor employeeExtractor(Employee... employee) {
        return new EmployeeExtractor() {
            final Map<EmployeeId, Employee> storage = Arrays.stream(employee)
                    .collect(Collectors.toMap(Employee::getId, empl -> empl));

            public Optional<Employee> getByPassportId(EmployeePassportId passportId) {
                return storage.values().stream()
                        .filter(empl -> empl.getPassportId().equals(passportId))
                        .findFirst();
            }

            public List<Employee> getAll() {
                return new ArrayList<>(storage.values());
            }
        };
    }
}
