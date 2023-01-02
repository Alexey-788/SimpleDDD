package com.alex788.employee.usecase.scenario;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.domain.EmployeeAlreadyExists;
import com.alex788.ddd.employee.domain.EmployeeId;
import com.alex788.employee.usecase.AddEmployee;
import com.alex788.employee.usecase.access.EmployeePersister;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddEmployeeUseCase implements AddEmployee {

    private final EmployeePersister persister;
    private final EmployeeAlreadyExists alreadyExists;
    private final EmployeeId.EmployeeIdGenerator idGenerator;

    @Override
    public Either<AddEmployeeError, EmployeeId> execute(AddEmployeeRequest request) {
        var employeeEth = Employee.create(
                idGenerator,
                alreadyExists,
                request.getPassportId(),
                request.getName(),
                request.getDepartment(),
                request.getPosition()
        );

        if (employeeEth.isLeft())
            return Either.left(new EmployeeAlreadyExistsError());

        Employee employee = employeeEth.get();
        persister.save(employee);
        return Either.right(employee.getId());
    }
}
