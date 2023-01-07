package com.alex788.ddd.employee.domain;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.alex788.ddd.employee.domain.Fixtures.*;

class EmployeeTest {

    @Test
    void addToStuff_InInvariant_CreatesEmployeeSuccessfully() {
        EmployeeId id = employeeId();
        EmployeePassportId passportId = employeePassportId();
        EmployeeName name = employeeName();
        EmployeeDepartment department = employeeDepartment();
        EmployeePosition position = employeePosition();

        Either<Employee.AddToStuffError, Employee> employeeEth = Employee.addToStuff(
                idGeneratorThatReturns(id),
                employeeNotExists(),
                passportId,
                name,
                department,
                position
        );

        assertTrue(employeeEth.isRight());
        Employee employee = employeeEth.get();
        assertEquals(id, employee.getId());
        assertEquals(passportId, employee.getPassportId());
        assertEquals(name, employee.getName());
        assertEquals(department, employee.getDepartment());
        assertEquals(position, employee.getPosition());
    }

    @Test
    void addToStuff_OutOfInvariant_CreatesEmployeeSuccessfully() {
        Employee.addToStuff(
                () -> new EmployeeId(1),
                employeeExists(),
                employeePassportId(),
                employeeName(),
                employeeDepartment(),
                employeePosition()
        );
    }

    EmployeeId.EmployeeIdGenerator idGeneratorThatReturns(EmployeeId id) {
        return () -> id;
    }

    EmployeeAlreadyExists employeeExists() {
        return passportId -> true;
    }
    EmployeeAlreadyExists employeeNotExists() {
        return passportId -> false;
    }
}