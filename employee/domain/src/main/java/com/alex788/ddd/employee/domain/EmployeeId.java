package com.alex788.ddd.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeId {

    private final long value;

    public interface EmployeeIdGenerator {
        EmployeeId generate();
    }
}
