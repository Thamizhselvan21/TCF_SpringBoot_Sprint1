// This program defines the Employee REST Controller
// to perform CRUD operations using Spring Boot REST APIs.
package com.tcf.hrms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcf.hrms.entity.Employee;
import com.tcf.hrms.service.EmployeeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    // Constructor to inject the EmployeeService dependency.
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // This method creates a new employee record.
    // POST API - Returns HTTP 201 Created.
    @PostMapping
    public ResponseEntity<Employee> create(
            @Valid @RequestBody Employee employee) {

        employee.setId(null);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createEmployee(employee));
    }

    // This method retrieves all employee records.
    // GET API - Returns the list of employees.
    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    // This method retrieves an employee by their ID.
    // GET API - Returns HTTP 404 if the employee is not found.
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    // This method updates an existing employee record.
    // PUT API - Validates and updates employee details.
    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {

        return service.updateEmployee(id, employee);
    }

    // This method deletes an employee record by ID.
    // DELETE API - Returns HTTP 204 No Content.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
/*
 * Status	Meaning	HRMS Example
200	OK	Employee retrieved
201	Created	Employee registered
400	Bad Request	Invalid salary or email
404	Not Found	Employee ID missing
409	Conflict	Duplicate employee email
500	Internal Server Error	Unexpected server failure
*/