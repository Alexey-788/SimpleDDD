package com.alex788.ddd.employee.persistence;

import com.alex788.ddd.employee.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.alex788.ddd.employee.domain.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryEmployeeRepositoryTest {

    InMemoryEmployeeRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new InMemoryEmployeeRepository();
    }

    @Test
    void save_EmployeeDidntExist_StoresOneIntoStorage() {
        Employee employee = employee();

        repository.save(employee);

        assertEquals(1, repository.storage.size());
        Employee storedEmployee = repository.storage.get(employee.getId());
        assertSame(employee, storedEmployee);
    }

    @Test
    void save_EmployeeExisted_RestoresOneIntoStorage() {
        EmployeeId id = employeeId();
        Employee oldEmployee = employee(id);
        Employee newEmployee = employee(id);

        repository.save(oldEmployee);
        repository.save(newEmployee);

        assertEquals(1, repository.storage.size());
        Employee storedEmployee = repository.storage.get(id);
        assertSame(newEmployee, storedEmployee);
        assertNotSame(oldEmployee, storedEmployee);
    }

    @Test
    void getByPassportId_EmployeeExisted_ReturnsEmployee() {
        EmployeePassportId passportId = employeePassportId();
        Employee employee = employee(passportId);
        repository.storage.put(employee.getId(), employee);

        Optional<Employee> storedEmployeeOpt = repository.getByPassportId(passportId);

        assertTrue(storedEmployeeOpt.isPresent());
        assertSame(employee, storedEmployeeOpt.get());
    }

    @Test
    void getByPassportId_EmployeeDidntExist_ReturnsEmpty() {
        EmployeePassportId passportId = employeePassportId();

        Optional<Employee> storedEmployeeOpt = repository.getByPassportId(passportId);

        assertTrue(storedEmployeeOpt.isEmpty());
    }

    @Test
    void getAll_EmployeeDoesntExist_ReturnsEmptyList() {
        List<Employee> storedEmployees = repository.getAll();

        assertEquals(0, storedEmployees.size());
    }

    @Test
    void getAll_EmployeesExist_ReturnsStoredEmployees() {
        Employee employee1 = employee(new EmployeeId(1));
        Employee employee2 = employee(new EmployeeId(2));
        repository.storage.put(employee1.getId(), employee1);
        repository.storage.put(employee2.getId(), employee2);

        List<Employee> employees = repository.getAll();

        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }
}