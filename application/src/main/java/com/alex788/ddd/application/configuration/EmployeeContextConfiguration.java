package com.alex788.ddd.application.configuration;

import com.alex788.ddd.employee.domain.EmployeeAlreadyExists;
import com.alex788.ddd.employee.domain.EmployeeId;
import com.alex788.ddd.employee.persistence.InMemoryEmployeeIdGenerator;
import com.alex788.ddd.employee.persistence.InMemoryEmployeeRepository;
import com.alex788.ddd.employee.presentation.AddEmployeeController;
import com.alex788.ddd.employee.presentation.GetAllEmployeesController;
import com.alex788.ddd.employee.usecase.AddEmployee;
import com.alex788.ddd.employee.usecase.GetAllEmployees;
import com.alex788.ddd.employee.usecase.access.EmployeeExtractor;
import com.alex788.ddd.employee.usecase.access.EmployeePersister;
import com.alex788.ddd.employee.usecase.invariant.EmployeeAlreadyExistsImpl;
import com.alex788.ddd.employee.usecase.scenario.AddEmployeeUseCase;
import com.alex788.ddd.employee.usecase.scenario.GetAllEmployeesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeContextConfiguration {

    @Bean
    AddEmployee addEmployee(
            EmployeePersister employeePersister,
            EmployeeAlreadyExists employeeAlreadyExists,
            EmployeeId.EmployeeIdGenerator employeeIdGenerator
    ) {
        return new AddEmployeeUseCase(
                employeePersister,
                employeeAlreadyExists,
                employeeIdGenerator
        );
    }

    @Bean
    GetAllEmployees getAllEmployees(EmployeeExtractor employeeExtractor) {
        return new GetAllEmployeesUseCase(employeeExtractor);
    }

    @Bean
    EmployeeId.EmployeeIdGenerator employeeIdGenerator() {
        return new InMemoryEmployeeIdGenerator();
    }

    @Bean
    EmployeeAlreadyExists employeeAlreadyExists(EmployeeExtractor employeeExtractor) {
        return new EmployeeAlreadyExistsImpl(employeeExtractor);
    }

    @Bean
    InMemoryEmployeeRepository employeeReposito() {
        return new InMemoryEmployeeRepository();
    }

    @Bean
    AddEmployeeController addEmployeeController(AddEmployee addEmployee) {
        return new AddEmployeeController(addEmployee);
    }

    @Bean
    GetAllEmployeesController getAllEmployeesController(GetAllEmployees getAllEmployees) {
        return new GetAllEmployeesController(getAllEmployees);
    }
}
