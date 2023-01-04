package com.alex788.ddd.employee.persistence;

import com.alex788.ddd.employee.domain.Employee;
import com.alex788.ddd.employee.domain.EmployeeId;
import com.alex788.ddd.employee.domain.EmployeePassportId;
import com.alex788.ddd.employee.usecase.access.EmployeeExtractor;
import com.alex788.ddd.employee.usecase.access.EmployeePersister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryEmployeeRepository implements EmployeePersister, EmployeeExtractor {

    protected final Map<EmployeeId, Employee> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<Employee> getByPassportId(EmployeePassportId passportId) {
        return storage.values().stream()
                .filter(employee -> employee.getPassportId().equals(passportId))
                .findFirst();
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void save(Employee employee) {
        storage.put(employee.getId(), employee);
    }
}
