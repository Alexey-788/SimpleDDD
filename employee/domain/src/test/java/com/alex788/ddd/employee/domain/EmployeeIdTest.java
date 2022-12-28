package com.alex788.ddd.employee.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeIdTest {

    @ParameterizedTest
    @ValueSource(longs = {-10, 0, 15})
    void createsSuccessfully(long value) {
        EmployeeId employeeId = new EmployeeId(value);

        assertEquals(value, employeeId.getValue());
    }
}