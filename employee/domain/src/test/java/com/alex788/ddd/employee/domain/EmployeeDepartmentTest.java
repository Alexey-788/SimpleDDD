package com.alex788.ddd.employee.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDepartmentTest {

    @ParameterizedTest
    @ValueSource(strings = {"Department", "Serious Big Department"})
    void from_WithValidValue_CreatesSuccessfully(String value) {
        var departmentEth = EmployeeDepartment.from(value);

        assertTrue(departmentEth.isRight());
        assertEquals(value, departmentEth.get().getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\n  \t "})
    void from_WithBlankValue_ReturnsError(String value) {
        var departmentEth = EmployeeDepartment.from(value);

        assertTrue(departmentEth.isLeft());
        assertInstanceOf(EmployeeDepartment.EmptyEmployeeDepartmentError.class, departmentEth.getLeft());
    }
}