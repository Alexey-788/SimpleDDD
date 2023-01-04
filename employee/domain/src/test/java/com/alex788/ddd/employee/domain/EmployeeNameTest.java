package com.alex788.ddd.employee.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"Name", "FirstName SecondName"})
    void from_WithValidValue_CreatesSuccessfully(String value) {
        var nameEth = EmployeeName.from(value);

        assertTrue(nameEth.isRight());
        assertEquals(value, nameEth.get().getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\n  \t "})
    void from_WithBlankValue_ReturnsError(String value) {
        var nameEth = EmployeeName.from(value);

        assertTrue(nameEth.isLeft());
        assertInstanceOf(EmployeeName.EmptyEmployeeNameError.class, nameEth.getLeft());
    }
}