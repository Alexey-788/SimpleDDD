package com.alex788.ddd.employee.presentation;

import com.alex788.ddd.employee.domain.EmployeeDepartment;
import com.alex788.ddd.employee.domain.EmployeeName;
import com.alex788.ddd.employee.domain.EmployeePassportId;
import com.alex788.ddd.employee.domain.EmployeePosition;
import com.alex788.ddd.employee.usecase.AddEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(URLs.EMPLOYEE_ROOT)
@RequiredArgsConstructor
public class AddEmployeeController {

    private final AddEmployee addEmployee;

    @PostMapping(URLs.ADD_EMPLOYEE)
    public String addEmployee(
            @Valid @RequestBody AddEmployeeJson addEmployeeJson
    ) {
        String[] response = {"done"};

        addEmployee.execute(new AddEmployee.AddEmployeeRequest(
                EmployeePassportId.from(addEmployeeJson.getPassportId()).get(),
                EmployeeName.from(addEmployeeJson.getName()).get(),
                EmployeeDepartment.from(addEmployeeJson.getDepartment()).get(),
                EmployeePosition.from(addEmployeeJson.getPosition()).get()
        ));//TODO: validation

        return response[0];
    }

    @Getter
    @AllArgsConstructor
    private static class AddEmployeeJson {

        @NotNull private final Long passportId;
        @NotNull private final String name;
        @NotNull private final String department;
        @NotNull private final String position;
    }
}
