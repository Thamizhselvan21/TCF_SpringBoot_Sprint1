// This program defines a custom exception class to handle
// duplicate employee email addresses.
package com.tcf.hrms.exception;


public class DuplicateEmailException extends RuntimeException {

    // Constructor to initialize the exception with an error message.
    public DuplicateEmailException(String email) {

        // Passes the custom error message to the RuntimeException class.
        super("Employee email already exists: " + email);

    }
}
