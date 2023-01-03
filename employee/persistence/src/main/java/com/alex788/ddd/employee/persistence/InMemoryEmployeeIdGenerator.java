package com.alex788.ddd.employee.persistence;

import com.alex788.ddd.employee.domain.EmployeeId;

import java.util.concurrent.atomic.AtomicLong;

public class InMemoryEmployeeIdGenerator implements EmployeeId.EmployeeIdGenerator {

    private final AtomicLong id = new AtomicLong(0);

    @Override
    public EmployeeId generate() {
        return new EmployeeId(id.incrementAndGet());
    }
}
