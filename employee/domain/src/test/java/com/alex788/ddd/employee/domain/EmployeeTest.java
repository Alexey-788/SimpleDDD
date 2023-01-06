package com.alex788.ddd.employee.domain;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void addToStuff_InInvariant_CreatesEmployeeSuccessfully() {
        EmployeeId id = new EmployeeId(1);
        EmployeePassportId passportId = passportId();
        EmployeeName name = name();
        EmployeeDepartment department = department();
        EmployeePosition position = position();

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
                passportId(),
                name(),
                department(),
                position()
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

    EmployeePassportId passportId() {
        return EmployeePassportId.from(1234567890).get();
    }

    EmployeeName name() {
        return EmployeeName.from("Some Name").get();
    }

    EmployeeDepartment department() {
        return EmployeeDepartment.from("Some Department").get();
    }

    EmployeePosition position() {
        return EmployeePosition.from("Some Position").get();
    }
}