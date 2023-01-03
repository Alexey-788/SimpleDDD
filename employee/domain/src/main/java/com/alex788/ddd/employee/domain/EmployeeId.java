package com.alex788.ddd.employee.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class EmployeeId {

    private final long value;

    public interface EmployeeIdGenerator {
        EmployeeId generate();
    }
}
