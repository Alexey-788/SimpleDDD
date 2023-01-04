package com.alex788.ddd.employee.presentation;

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
        AddEmployee.AddEmployeeRequest.from(
                        addEmployeeJson.getPassportId(),
                        addEmployeeJson.getName(),
                        addEmployeeJson.getDepartment(),
                        addEmployeeJson.getPosition()
                )
                .peek(addEmployee::execute)
                .peekLeft(err -> response[0] = err.getMessage());

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
