package com.springboot.learning.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Base class for all soap faults thrown by the service.
 */
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1387749572104551287L;
    private final String code;
    private final String reason;
    private final String details;

    /**
     * Constructor.
     *
     * @param code
     *          error code
     * @param reason
     *          error reason
     * @param details
     *          error details
     * @param message
     *          error message
     */
    public BaseRuntimeException(final String code, final String reason, final String message, final String details) {
        super(message);
        this.code = code;
        this.reason = reason;
        this.details = details;
    }

    /**
     * Constructor.
     *
     * @param code
     *          error code
     * @param reason
     *          error reason
     * @param details
     *          error details
     * @param message
     *          error message
     * @param cause
     *          root cause
     */
    public BaseRuntimeException(final String code, final String reason, final String details, final String message,
                                final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.reason = reason;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public String getDetails() {
        return details;
    }

    public ExceptionResponse getExceptionResponse() {
        // don't return details to avoid returning too much info to caller
        return new ExceptionResponse( LocalDateTime.now(),this.code, this.reason, details);
    }

    @Override
    public String toString() {
       /* return ToStringBuilder.reflectionToString(this);*/
        return null;
    }

}