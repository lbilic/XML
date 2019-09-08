package com.uns.ac.rs.xml.controller;

import com.sun.org.apache.xml.internal.security.transforms.TransformationException;
import com.uns.ac.rs.xml.util.DatabaseConnectionException;
import com.uns.ac.rs.xml.util.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@CrossOrigin(origins = "http://localhost:4200")
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DatabaseConnectionException.class)
    public ResponseEntity<String> serveDatabaseConnectioException(DatabaseConnectionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> serveValidationException(ValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransformationException.class)
    public ResponseEntity<String> serveTransformationException(TransformationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
