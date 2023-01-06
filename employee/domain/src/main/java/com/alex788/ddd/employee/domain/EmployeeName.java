package com.alex788.ddd.employee.domain;

import com.alex788.ddd.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeName {

    private final String value;

    public static Either<Error, EmployeeName> from(String name) {
        if (name.isBlank()) {
            return Either.left(Error.BLANK_VALUE);
        }

        return Either.right(new EmployeeName(name));
    }

    public enum Error implements BusinessError {
        BLANK_VALUE
    }
}
