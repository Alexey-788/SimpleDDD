package com.alex788.ddd.employee.domain;

public interface EmployeeAlreadyExists {

    boolean check(EmployeePassportId passportId);
}
