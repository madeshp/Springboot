package com.springboot.learning.exception;
import com.springboot.learning.utils.Utility;

import static com.springboot.learning.constants. EmployeeConstants.*;
/**
 * Represents SoapFault for Technical Error.
 */
public class InternalException extends BaseRuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message
     *          error message
     * @param cause
     *          root cause
     */
    public InternalException(final String message, final Throwable cause) {
        super(ERROR_CODE_INTERNAL, FAULT_STRING, getMessage(message, cause), FAULT_STRING, cause);
    }

    private static String getMessage(final String message, final Throwable cause) {
        String detail = message;
        if (cause != null) {
            detail = message + ", " + Utility.getStackTrace(cause);
        }
        return detail;
    }

}