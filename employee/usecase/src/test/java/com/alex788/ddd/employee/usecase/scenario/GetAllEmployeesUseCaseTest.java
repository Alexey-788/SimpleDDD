package com.alex788.ddd.employee.usecase.scenario;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.domain.EmployeeId;
import com.alex788.ddd.employee.usecase.GetAllEmployees;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.alex788.ddd.employee.domain.Fixtures.employee;
import static com.alex788.ddd.employee.usecase.Fixtures.employeeExtractor;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllEmployeesUseCaseTest {

    @Test
    void execute_ReturnsCorrectParsedList() {
        Employee[] employees = {
                employee(new EmployeeId(1)),
                employee(new EmployeeId(2))
        };
        GetAllEmployeesUseCase useCase = new GetAllEmployeesUseCase(employeeExtractor(employees));

        List<GetAllEmployees.EmployeeInfo> employeeInfos = useCase.execute();

        assertEquals(employees.length, employeeInfos.size());
        assertEmployeeInfoEqualsEmployee(employeeInfos.get(0), employees[0]);
        assertEmployeeInfoEqualsEmployee(employeeInfos.get(1), employees[1]);
    }

    void assertEmployeeInfoEqualsEmployee(GetAllEmployees.EmployeeInfo employeeInfo, Employee employee) {
        assertEquals(employee.getPassportId(), employeeInfo.getPassportId());
        assertEquals(employee.getName(), employeeInfo.getName());
        assertEquals(employee.getDepartment(), employeeInfo.getDepartment());
        assertEquals(employee.getPosition(), employeeInfo.getPosition());
    }
}