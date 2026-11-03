
package com.tcf.hrms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcf.hrms.entity.Employee;
import com.tcf.hrms.repository.EmployeeRepository;
import com.tcf.hrms.exception.*;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Employee createEmployee(Employee employee) {

        if (repository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException(employee.getEmail());
        }

        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Transactional
    public Employee updateEmployee(
            Long id, Employee details) {

        Employee employee = getEmployeeById(id);

        if (!employee.getEmail().equals(details.getEmail())
                && repository.existsByEmail(details.getEmail())) {
            throw new DuplicateEmailException(
                details.getEmail());
        }

        employee.setName(details.getName());
        employee.setEmail(details.getEmail());
        employee.setSalary(details.getSalary());

        return repository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);
        repository.delete(employee);
    }
}
