package com.alex788.ddd.employee.usecase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddEmployeeRequestTest {

    @Test
    void from_WithValidArgs_CreatesSuccessfully() {
        var requestEth = AddEmployee.AddEmployeeRequest.from(
                rawPassportId(),
                rawName(),
                rawDepartment(),
                rawPosition()
        );

        assertTrue(requestEth.isRight());
    }

    @Test
    void from_WithInvalidPassportId_ReturnsError() {
        long invalidPassportId = 123L;

        var requestEth = AddEmployee.AddEmployeeRequest.from(
                invalidPassportId,
                rawName(),
                rawDepartment(),
                rawPosition()
        );

        assertTrue(requestEth.isLeft());
    }

    @Test
    void from_WithInvalidName_ReturnsError() {
        String invalidName = "\n \t";

        var requestEth = AddEmployee.AddEmployeeRequest.from(
                rawPassportId(),
                invalidName,
                rawDepartment(),
                rawPosition()
        );

        assertTrue(requestEth.isLeft());
    }

    @Test
    void from_WithInvalidDepartment_ReturnsError() {
        String invalidDepartment = "\n \t";

        var requestEth = AddEmployee.AddEmployeeRequest.from(
                rawPassportId(),
                rawName(),
                invalidDepartment,
                rawPosition()
        );

        assertTrue(requestEth.isLeft());
    }

    @Test
    void from_WithInvalidPosition_ReturnsError() {
        String invalidPosition = "\n \t";

        var requestEth = AddEmployee.AddEmployeeRequest.from(
                rawPassportId(),
                rawName(),
                rawDepartment(),
                invalidPosition
        );

        assertTrue(requestEth.isLeft());
    }

    private long rawPassportId() {
        return 1234567890L;
    }

    private String rawName() {
        return "Some Name";
    }

    private String rawDepartment() {
        return "Some Department";
    }

    private String rawPosition() {
        return "Some Position";
    }
}
