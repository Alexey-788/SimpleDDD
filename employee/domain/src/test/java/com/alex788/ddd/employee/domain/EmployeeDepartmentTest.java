package com.alex788.ddd.employee.domain;

import io.vavr.control.Either;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDepartmentTest {

    @ParameterizedTest
    @ValueSource(strings = {"Department", "Serious Big Department"})
    void from_WithValidValue_CreatesSuccessfully(String value) {
        Either<EmployeeDepartment.Error, EmployeeDepartment> departmentEth = EmployeeDepartment.from(value);

        assertTrue(departmentEth.isRight());
        assertEquals(value, departmentEth.get().getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\n  \t "})
    void from_WithBlankValue_ReturnsError(String value) {
        Either<EmployeeDepartment.Error, EmployeeDepartment> departmentEth = EmployeeDepartment.from(value);

        assertTrue(departmentEth.isLeft());
        assertEquals(EmployeeDepartment.Error.BLANK_VALUE, departmentEth.getLeft());
    }
}