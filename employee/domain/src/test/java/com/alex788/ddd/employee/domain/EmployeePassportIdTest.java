package com.alex788.ddd.employee.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeePassportIdTest {

    @ParameterizedTest
    @ValueSource(longs = {11_22_333333L, 99_88_777777L})
    void from_WithValidValue_CreatesSuccessfully(long value) {
        var passportIdEth = EmployeePassportId.from(value);

        assertTrue(passportIdEth.isRight());
        assertEquals(value, passportIdEth.get().getValue());
    }

    @ParameterizedTest
    @ValueSource(longs = {111_22_333333L, 9_88_777777L})
    void from_WithWrongIdLength_ReturnsError(long value) {
        var passportIdEth = EmployeePassportId.from(value);

        assertTrue(passportIdEth.isLeft());
        assertInstanceOf(EmployeePassportId.PassportIdHasInvalidLengthError.class, passportIdEth.getLeft());
    }
}