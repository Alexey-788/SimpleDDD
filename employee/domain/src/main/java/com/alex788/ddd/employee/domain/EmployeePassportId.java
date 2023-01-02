package com.alex788.ddd.employee.domain;

import com.alex788.ddd.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeePassportId {

    private final static int PASSPORT_ID_LENGTH = 10;

    private final long value;

    public static Either<PassportIdHasInvalidLengthError, EmployeePassportId> from(long passportId) {
        if (String.valueOf(passportId).length() != PASSPORT_ID_LENGTH) {
            return Either.left(new PassportIdHasInvalidLengthError());
        }

        return Either.right(new EmployeePassportId(passportId));
    }

    public static class PassportIdHasInvalidLengthError implements BusinessError {}
}
