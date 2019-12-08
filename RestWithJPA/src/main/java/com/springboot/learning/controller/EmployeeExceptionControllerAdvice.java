package com.springboot.learning.controller;


import com.springboot.learning.constants.EmployeeConstants;
import com.springboot.learning.exception.BadRequestException;
import com.springboot.learning.exception.EmployeeNotFoundException;
import com.springboot.learning.exception.ExceptionResponse;
import com.springboot.learning.exception.InternalException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.springboot.learning")
@RequestMapping(produces = "application/json")
@ConditionalOnProperty(name = "employee.exceptioncontroller.advice",
        havingValue = "true")
@Profile(value = "dev")
public class EmployeeExceptionControllerAdvice extends ResponseEntityExceptionHandler {
   // private static final Logger LOG = LoggerFactory.getLogger(EmployeeExceptionControllerAdvice.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    protected ResponseEntity<Object> employeeNotFoundException(final EmployeeNotFoundException ex/*,
                                                               final HttpHeaders headers, final HttpStatus status, final WebRequest request*/) {
        //headers.set(EmployeeConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
      /*  final List<String> errors = new ArrayList<>();
        errors.add( "Invalid Input: firstName/lastName not present in the JSON Request " );*/
        /*for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " );
        }
        for (final ObjectError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }*/
        final ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), EmployeeConstants.ERROR_CODE_INVALID_SERVICE_REQUEST,
                        EmployeeConstants.ERROR_REASON_INVALID_SERVICE_REQUEST, ex.getReason() + ":" + ex.getDetails());
       /* if (LOG.isErrorEnabled()) {
            LOG.error(String.format("handleMethodArgumentNotValid, %s", exceptionResponse.toString()));
        }*/
        return new ResponseEntity<>(exceptionResponse, /*headers,*/ HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    /**
     * Handles Internal Exceptions
     *
     * @param ex      - internal exception
     * @param request - web request
     * @return - error details
     */
    @ExceptionHandler(InternalException.class)
    public final ResponseEntity<ExceptionResponse> handleException(final InternalException ex, final WebRequest request) {
        return new ResponseEntity<>(ex.getExceptionResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles Invalid Service Request Exceptions
     *
     * @param ex      - invalid service request exception
     * @param request - web request
     * @return - error details
     */
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleException(final BadRequestException ex,
                                                                   final WebRequest request) {
        return new ResponseEntity<>(ex.getExceptionResponse(), HttpStatus.BAD_REQUEST);
    }

    /*Entity bean validation error*/
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request) {
       // LOG.info(ex.getConstraintViolations().toString());
        List<String> details = ex.getConstraintViolations()
                .parallelStream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());
        final ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), EmployeeConstants.ERROR_CODE_INVALID_SERVICE_REQUEST,
                        EmployeeConstants.ERROR_REASON_INVALID_SERVICE_REQUEST, details.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /*Default Exception handling*/
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        final ExceptionResponse exceptionResponse =
                new ExceptionResponse(LocalDateTime.now(), EmployeeConstants.ERROR_CODE_INVALID_SERVICE_REQUEST,
                        EmployeeConstants.ERROR_REASON_INVALID_SERVICE_REQUEST, ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}