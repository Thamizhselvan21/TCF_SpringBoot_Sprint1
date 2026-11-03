// This program defines the Employee Entity class for the Employee Management System.
package com.tcf.hrms.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "employees",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Employee {

    // Employee ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Employee Name
    @NotBlank(message = "Name is required")
    private String name;

    // Employee Email
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    // Employee Salary
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false,message = "Salary must be greater than zero")
    private BigDecimal salary;

    // Getter and Setter for ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for Salary
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
