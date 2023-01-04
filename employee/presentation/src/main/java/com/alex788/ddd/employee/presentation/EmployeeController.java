package com.alex788.ddd.employee.presentation;

import com.alex788.ddd.employee.usecase.AddEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLs.EMPLOYEE_ROOT)
@RequiredArgsConstructor
public class EmployeeController {

    private final AddEmployee addEmployee;

    @PostMapping(URLs.ADD_EMPLOYEE)
    public String addEmployee(
            @RequestParam long passportId,
            @RequestParam String name,
            @RequestParam String department,
            @RequestParam String position
    ) {
        String[] response = {"done"};
        AddEmployee.AddEmployeeRequest.from(
                        passportId,
                        name,
                        department,
                        position
                )
                .peek(addEmployee::execute)
                .peekLeft(err -> response[0] = err.getMessage());

        return response[0];
    }
}
