package com.alex788.ddd.employee.persistence;

import com.alex788.ddd.employee.domain.EmployeeId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryEmployeeIdGeneratorTest {

    InMemoryEmployeeIdGenerator idGenerator;

    @BeforeEach
    void beforeEach() {
        idGenerator = new InMemoryEmployeeIdGenerator();
    }

    @Test
    void generate_ReturnsDifferentIds() {
        EmployeeId id1 = idGenerator.generate();
        EmployeeId id2 = idGenerator.generate();

        assertNotEquals(id1, id2);
        assertNotEquals(id1.getValue(), id2.getValue());
    }
}