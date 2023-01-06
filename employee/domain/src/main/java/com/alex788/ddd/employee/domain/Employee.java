package com.alex788.ddd.employee.domain;

import com.alex788.ddd.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {

    private final EmployeeId id;
    private final EmployeePassportId passportId;
    private final EmployeeName name;
    private final EmployeeDepartment department;
    private final EmployeePosition position;

    public static Either<AddToStuffError, Employee> addToStuff(
            EmployeeId.EmployeeIdGenerator idGenerator,
            EmployeeAlreadyExists alreadyExists,
            EmployeePassportId passportId,
            EmployeeName name,
            EmployeeDepartment department,
            EmployeePosition position
    ) {
        if (alreadyExists.check(passportId)) {
            return Either.left(AddToStuffError.ALREADY_EXISTS_WITH_SAME_PASSPORT_ID);
        }

        EmployeeId id = idGenerator.generate();

        return Either.right(new Employee(
                id,
                passportId,
                name,
                department,
                position
        ));
    }

    public enum AddToStuffError implements BusinessError {
        ALREADY_EXISTS_WITH_SAME_PASSPORT_ID
    }
}
