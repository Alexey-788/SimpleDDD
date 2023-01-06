package com.alex788.ddd.employee.usecase;

import com.alex788.ddd.common.types.error.BusinessError;
import com.alex788.ddd.employee.domain.*;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AddEmployee {

    Either<AddEmployeeError, EmployeeId> execute(AddEmployeeRequest request);

    enum AddEmployeeError implements BusinessError {
        ALREADY_EXISTS_WITH_SAME_PASSPORT_ID
    }

    @Getter
    @AllArgsConstructor
    class AddEmployeeRequest {

        private final EmployeePassportId passportId;
        private final EmployeeName name;
        private final EmployeeDepartment department;
        private final EmployeePosition position;
    }
}
