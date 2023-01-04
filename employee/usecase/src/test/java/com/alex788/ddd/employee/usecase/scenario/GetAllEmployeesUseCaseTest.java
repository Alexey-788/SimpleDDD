package com.alex788.ddd.employee.usecase.scenario;

import com.alex788.ddd.employee.domain.*;
import com.alex788.ddd.employee.usecase.GetAllEmployees;
import com.alex788.ddd.employee.usecase.access.EmployeeExtractor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllEmployeesUseCaseTest {

    @Test
    void execute_ReturnsCorrectParsedList() {
        List<Employee> employees = List.of(
                employeeFrom(1, 1111111111, "name 1", "department 1", "position 1"),
                employeeFrom(2, 1111111112, "name 2", "department 2", "position 2")
        );
        GetAllEmployeesUseCase useCase = new GetAllEmployeesUseCase(employeeExtractorReturns(employees));

        List<GetAllEmployees.EmployeeInfo> employeeInfos = useCase.execute();

        assertEquals(employees.size(), employeeInfos.size());
        assertEmployeeInfoEqualsEmployee(employeeInfos.get(0), employees.get(0));
        assertEmployeeInfoEqualsEmployee(employeeInfos.get(1), employees.get(1));
    }

    void assertEmployeeInfoEqualsEmployee(GetAllEmployees.EmployeeInfo employeeInfo, Employee employee) {
        assertEquals(employee.getPassportId(), employeeInfo.getPassportId());
        assertEquals(employee.getName(), employeeInfo.getName());
        assertEquals(employee.getDepartment(), employeeInfo.getDepartment());
        assertEquals(employee.getPosition(), employeeInfo.getPosition());
    }

    Employee employeeFrom(long id, long passportId, String name, String department, String position) {
        return Employee.create(
                () -> new EmployeeId(id),
                (pId) -> false,
                EmployeePassportId.from(passportId).get(),
                EmployeeName.from(name).get(),
                EmployeeDepartment.from(department).get(),
                EmployeePosition.from(position).get()
        ).get();
    }

    EmployeeExtractor employeeExtractorReturns(List<Employee> employees) {
        return new EmployeeExtractor() {
            @Override
            public List<Employee> getAll() {
                return employees;
            }

            @Override
            public Optional<Employee> getByPassportId(EmployeePassportId passportId) {
                throw new UnsupportedOperationException();
            }
        };
    }
}