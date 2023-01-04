package com.alex788.ddd.employee.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeePositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"Manager", "Big Boss"})
    void from_WithValidValue_CreatesSuccessfully(String value) {
        var positionEth = EmployeePosition.from(value);

        assertTrue(positionEth.isRight());
        assertEquals(value, positionEth.get().getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\n  \t "})
    void from_WithBlankValue_ReturnsError(String value) {
        var positionEth = EmployeePosition.from(value);

        assertTrue(positionEth.isLeft());
        assertInstanceOf(EmployeePosition.EmptyEmployeePositionError.class, positionEth.getLeft());
    }
}