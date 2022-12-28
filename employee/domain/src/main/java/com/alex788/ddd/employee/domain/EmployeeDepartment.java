package com.alex788.ddd.employee.domain;

import com.alex788.ddd.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDepartment {

    private final String value;

    public static Either<EmptyEmployeeDepartmentError, EmployeeDepartment> from(String department) {
        if (department.isBlank()) {
            return Either.left(new EmptyEmployeeDepartmentError());
        }

        return Either.right(new EmployeeDepartment(department));
    }

    public static class EmptyEmployeeDepartmentError implements BusinessError {}
}
