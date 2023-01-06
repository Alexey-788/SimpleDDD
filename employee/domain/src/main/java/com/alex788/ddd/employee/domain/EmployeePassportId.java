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
public class EmployeePassportId {

    private final static int PASSPORT_ID_LENGTH = 10;

    private final long value;

    public static Either<Error, EmployeePassportId> from(long passportId) {
        if (String.valueOf(passportId).length() != PASSPORT_ID_LENGTH) {
            return Either.left(Error.INVALID_LENGTH);
        }

        return Either.right(new EmployeePassportId(passportId));
    }

    public enum Error implements BusinessError {
        INVALID_LENGTH
    }
}
