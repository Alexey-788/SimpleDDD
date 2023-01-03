package com.alex788.ddd.employee.usecase;

import com.alex788.ddd.common.types.error.BusinessError;
import com.alex788.ddd.employee.domain.*;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AddEmployee {

    Either<AddEmployeeError, EmployeeId> execute(AddEmployeeRequest request);

    interface AddEmployeeError extends BusinessError {}
    class EmployeeAlreadyExistsError implements AddEmployeeError {}

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class AddEmployeeRequest {

        private final EmployeePassportId passportId;
        private final EmployeeName name;
        private final EmployeeDepartment department;
        private final EmployeePosition position;

        public static Either<InvalidEmployeeParameter, AddEmployeeRequest> from(
                long passportId,
                String name,
                String department,
                String position
        ) {
            var passportIdEth = EmployeePassportId.from(passportId);
            if (passportIdEth.isLeft())
                return Either.left(InvalidEmployeeParameter.from(passportIdEth.getLeft()));

            var nameEth = EmployeeName.from(name);
            if (nameEth.isLeft())
                return Either.left(InvalidEmployeeParameter.from(nameEth.getLeft()));

            var departmentEth = EmployeeDepartment.from(department);
            if (departmentEth.isLeft())
                return Either.left(InvalidEmployeeParameter.from(departmentEth.getLeft()));

            var positionEth = EmployeePosition.from(position);
            if (positionEth.isLeft())
                return Either.left(InvalidEmployeeParameter.from(positionEth.getLeft()));

            return Either.right(
                    new AddEmployeeRequest(
                            passportIdEth.get(),
                            nameEth.get(),
                            departmentEth.get(),
                            positionEth.get()
                    )
            );
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class InvalidEmployeeParameter {

        private final String message;

        public static InvalidEmployeeParameter from(EmployeePassportId.PassportIdHasInvalidLengthError error) {
            return new InvalidEmployeeParameter("Invalid passport id length");
        }

        public static InvalidEmployeeParameter from(EmployeeName.EmptyEmployeeNameError error) {
            return new InvalidEmployeeParameter("Empty name");
        }

        public static InvalidEmployeeParameter from(EmployeeDepartment.EmptyEmployeeDepartmentError error) {
            return new InvalidEmployeeParameter("Empty department");
        }

        public static InvalidEmployeeParameter from(EmployeePosition.EmptyEmployeePositionError error) {
            return new InvalidEmployeeParameter("Empty position");
        }
    }
}
