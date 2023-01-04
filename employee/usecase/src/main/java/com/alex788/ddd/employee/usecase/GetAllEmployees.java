package com.alex788.ddd.employee.usecase;

import com.alex788.ddd.employee.domain.EmployeeDepartment;
import com.alex788.ddd.employee.domain.EmployeeName;
import com.alex788.ddd.employee.domain.EmployeePassportId;
import com.alex788.ddd.employee.domain.EmployeePosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface GetAllEmployees {

    List<EmployeeInfo> execute();

    @Getter
    @AllArgsConstructor
    class EmployeeInfo {

        private final EmployeePassportId passportId;
        private final EmployeeName name;
        private final EmployeeDepartment department;
        private final EmployeePosition position;
    }
}
