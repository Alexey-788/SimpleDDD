package com.alex788.ddd.employee.domain;

import com.alex788.ddd.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeePosition {

    private final String value;

    public static Either<EmptyEmployeePositionError, EmployeePosition> from(String position) {
        if (position.isBlank()) {
            return Either.left(new EmptyEmployeePositionError());
        }

        return Either.right(new EmployeePosition(position));
    }

    public static class EmptyEmployeePositionError implements BusinessError {}
}
