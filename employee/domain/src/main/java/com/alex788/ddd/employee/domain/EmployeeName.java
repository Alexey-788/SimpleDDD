package com.alex788.ddd.employee.domain;

import com.alex788.ddd.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeName {

    private final String value;

    public static Either<EmptyEmployeeNameError, EmployeeName> from(String name) {
        if (name.isBlank()) {
            return Either.left(new EmptyEmployeeNameError());
        }

        return Either.right(new EmployeeName(name));
    }

    public static class EmptyEmployeeNameError implements BusinessError {}
}
