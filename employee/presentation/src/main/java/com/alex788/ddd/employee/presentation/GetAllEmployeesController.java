package com.alex788.ddd.employee.presentation;

import com.alex788.ddd.employee.usecase.GetAllEmployees;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.internal.Urls;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLs.EMPLOYEE_ROOT)
@RequiredArgsConstructor
public class GetAllEmployeesController {

    private final GetAllEmployees getAllEmployees;

    @GetMapping(URLs.GET_ALL_EMPLOYEE)
    public EmployeesJson getAll() {
        List<GetAllEmployees.EmployeeInfo> employeeInfos = getAllEmployees.execute();
        return new EmployeesJson(employeeInfos.stream()
                .map(employeeInfo -> new EmployeeJson(
                        employeeInfo.getPassportId().getValue(),
                        employeeInfo.getName().getValue(),
                        employeeInfo.getDepartment().getValue(),
                        employeeInfo.getPosition().getValue()
                ))
                .collect(Collectors.toList())
        );
    }

    @Getter
    @RequiredArgsConstructor
    public static class EmployeesJson {

        private final List<EmployeeJson> employeesJson;
    }

    @Getter
    @RequiredArgsConstructor
    public static class EmployeeJson {

        private final long passportId;
        private final String name;
        private final String department;
        private final String position;
    }
}
