// This program defines an ErrorResponse record to store
// and return standardized exception details in REST APIs.
package com.tcf.hrms.exception;

import java.time.LocalDateTime;

public record ErrorResponse(

    // Stores the date and time when the exception occurred.
    LocalDateTime timestamp,
    // Stores the HTTP status code (e.g., 400, 404, 500).
    int status,
    // Stores the HTTP error type (e.g., Bad Request, Not Found).
    String error,
    // Stores the detailed exception error message.
    String message,
    // Stores the API endpoint path where the error occurred.
    String path

) {}
