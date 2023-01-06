package com.alex788.ddd.employee.usecase.scenario;

import com.alex788.ddd.employee.domain.*;
import com.alex788.ddd.employee.usecase.AddEmployee;
import com.alex788.ddd.employee.usecase.access.EmployeePersister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddEmployeeUseCaseTest {

    @Test
    void execute_WithValidState_ReturnsId() {
        var employeeIdEth = new AddEmployeeUseCase(
                persister(),
                employeeNotExists(),
                idGenerator()
        ).execute(addEmployeeRequest());

        assertTrue(employeeIdEth.isRight());
    }

    @Test
    void execute_WithInvalidState_ReturnsError() {
        var employeeIdEth = new AddEmployeeUseCase(
                persister(),
                employeeExists(),
                idGenerator()
        ).execute(addEmployeeRequest());

        assertTrue(employeeIdEth.isLeft());
    }

    @Test
    void execute_WithValidState_CallsPersister() {
        EmployeePersister persister = mock(EmployeePersister.class);

        new AddEmployeeUseCase(
                persister,
                employeeNotExists(),
                idGenerator()
        ).execute(addEmployeeRequest());

        verify(persister, times(1)).save(any());
    }

    AddEmployee.AddEmployeeRequest addEmployeeRequest() {
        return new AddEmployee.AddEmployeeRequest(
                EmployeePassportId.from(1234567890).get(),
                EmployeeName.from("Some Name").get(),
                EmployeeDepartment.from("Some Department").get(),
                EmployeePosition.from("Some Positioon").get()
        );
    }

    EmployeeId.EmployeeIdGenerator idGenerator() {
        return () -> new EmployeeId(1234567890L);
    }

    EmployeePersister persister() {
        return employee -> {};
    }

    EmployeeAlreadyExists employeeExists() {
        return passportId -> true;
    }

    EmployeeAlreadyExists employeeNotExists() {
        return passportId -> false;
    }
}