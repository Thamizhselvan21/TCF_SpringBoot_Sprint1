// This program defines a custom exception class to handle
// employee records that are not found in the database.
package com.tcf.hrms.exception;


public class EmployeeNotFoundException extends RuntimeException {

    // Constructor to initialize the exception with the employee ID.
    public EmployeeNotFoundException(Long id) {

        // Passes the custom error message to the RuntimeException class.
        super("Employee not found with ID: " + id);

    }
}
