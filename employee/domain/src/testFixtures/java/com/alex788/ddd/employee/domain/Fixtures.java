package com.alex788.ddd.employee.domain;

import static com.alex788.ddd.common.types.Fixtures.faker;

public class Fixtures {

    public static Employee employee() {
        return Employee.addToStuff(
                Fixtures::employeeId,
                passportId -> false,
                employeePassportId(),
                employeeName(),
                employeeDepartment(),
                employeePosition()
        ).get();
    }

    public static Employee employee(EmployeeId id) {
        return Employee.addToStuff(
                () -> id,
                passportId -> false,
                employeePassportId(),
                employeeName(),
                employeeDepartment(),
                employeePosition()
        ).get();
    }

    public static Employee employee(EmployeePassportId passportId) {
        return Employee.addToStuff(
                Fixtures::employeeId,
                pId -> false,
                passportId,
                employeeName(),
                employeeDepartment(),
                employeePosition()
        ).get();
    }

    public static EmployeeId employeeId() {
        return new EmployeeId(faker.number().randomNumber());
    }

    public static EmployeePassportId employeePassportId() {
        return EmployeePassportId.from(
                faker.number().randomNumber(EmployeePassportId.LENGTH, true)
        ).get();
    }

    public static EmployeeName employeeName() {
        return EmployeeName.from(
                faker.name().fullName()
        ).get();
    }

    public static EmployeeDepartment employeeDepartment() {
        return EmployeeDepartment.from(
                faker.company().name()
        ).get();
    }

    public static EmployeePosition employeePosition() {
        return EmployeePosition.from(
                faker.job().position()
        ).get();
    }
}
