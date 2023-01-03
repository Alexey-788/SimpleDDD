package com.alex788.ddd.employee.persistence;

import com.alex788.ddd.employee.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryEmployeeRepositoryTest {

    InMemoryEmployeeRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new InMemoryEmployeeRepository();
    }

    @Test
    void save_EmployeeDidntExist_StoresOneIntoStorage() {
        Employee employee = employeeWithId(new EmployeeId(1));
        repository.save(employee);

        Employee storedEmployee = repository.storage.get(employee.getId());

        assertEquals(1, repository.storage.size());
        assertSame(employee, storedEmployee);
    }

    @Test
    void save_EmployeeExisted_RestoresOneIntoStorage() {
        EmployeeId id = new EmployeeId(1);
        Employee oldEmployee = employeeWithId(id);
        repository.save(oldEmployee);
        Employee newEmployee = employeeWithId(id);
        repository.save(newEmployee);

        Employee storedEmployee = repository.storage.get(id);

        assertEquals(1, repository.storage.size());
        assertSame(newEmployee, storedEmployee);
        assertNotSame(oldEmployee, storedEmployee);
    }

    @Test
    void getByPassportId_EmployeeExisted_ReturnsEmployee() {
        EmployeePassportId passportId = EmployeePassportId.from(1234567890L).get();
        Employee employee = employeeWithPassportId(passportId);
        repository.storage.put(employee.getId(), employee);

        Optional<Employee> storedEmployeeOpt = repository.getByPassportId(passportId);

        assertTrue(storedEmployeeOpt.isPresent());
        assertSame(employee, storedEmployeeOpt.get());
    }

    @Test
    void getByPassportId_EmployeeDidntExist_ReturnsEmployee() {
        EmployeePassportId passportId = EmployeePassportId.from(1234567890L).get();

        Optional<Employee> storedEmployeeOpt = repository.getByPassportId(passportId);

        assertTrue(storedEmployeeOpt.isEmpty());
    }

    Employee employeeWithId(EmployeeId id) {
        return Employee.create(
                () -> id,
                (passportId) -> false,
                EmployeePassportId.from(1234567890L).get(),
                EmployeeName.from("Some Name").get(),
                EmployeeDepartment.from("Some Department").get(),
                EmployeePosition.from("Some Position").get()
        ).get();
    }

    Employee employeeWithPassportId(EmployeePassportId passportId) {
        return Employee.create(
                () -> new EmployeeId(1),
                (pId) -> false,
                passportId,
                EmployeeName.from("Some Name").get(),
                EmployeeDepartment.from("Some Department").get(),
                EmployeePosition.from("Some Position").get()
        ).get();
    }
}