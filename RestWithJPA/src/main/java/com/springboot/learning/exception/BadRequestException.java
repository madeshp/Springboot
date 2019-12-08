package com.springboot.learning.exception;

import static com.springboot.learning.constants.EmployeeConstants.*;

public class BadRequestException extends BaseRuntimeException {
    private static final long serialVersionUID = 8324260490058676581L;

    public BadRequestException(final String message) {
        super(ERROR_CODE_INVALID_SERVICE_REQUEST, ERROR_REASON_INVALID_SERVICE_REQUEST, message, message);
    }
}
