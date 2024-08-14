package com.learn_rest_api.rest_demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

  // Add logger for logging
  private static final Logger logger = LoggerFactory.getLogger(StudentRestExceptionHandler.class);

  // add exception handling code
  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
    return buildResponseEntity(exc, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
    return buildResponseEntity(exc, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<StudentErrorResponse> buildResponseEntity(Exception exc, HttpStatus status) {
    logger.error("Exception: ", exc);

    StudentErrorResponse error = new StudentErrorResponse(
        status.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }
}