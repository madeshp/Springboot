package com.springboot.learning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.springboot.learning.constants.EmployeeConstants.*;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException  extends BaseRuntimeException {
    private static final long serialVersionUID = 8324260490058676561L;

    public EmployeeNotFoundException (final String message,final String details) {
        super(ERROR_CODE_EMPLOYEE_NOT_FOUND, ERROR_REASON_EMPLOYEE_NOT_FOUND, message, details);
    }
    }

