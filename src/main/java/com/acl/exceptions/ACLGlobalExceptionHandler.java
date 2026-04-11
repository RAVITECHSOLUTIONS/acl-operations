package com.acl.exceptions;

import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ACLGlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    String errorCode = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    ErrorResponse errorResponse = new ErrorResponse(errorCode,
        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleNullPointerException(NullPointerException ex,
      WebRequest request) {
    String errorCode = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    ErrorResponse errorResponse = new ErrorResponse(errorCode,
        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex,
      WebRequest request) {
    String errorCode = String.valueOf(HttpStatus.NOT_FOUND.value());
    ErrorResponse errorResponse = new ErrorResponse(errorCode,
        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

//  @ExceptionHandler(AccessDeniedException.class)
//  @ResponseStatus(HttpStatus.UNAUTHORIZED)
//  public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex,
//      WebRequest request) {
//    String errorCode = String.valueOf(HttpStatus.UNAUTHORIZED.value());
//    ErrorResponse errorResponse = new ErrorResponse(errorCode,
//        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
//    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex, WebRequest request) {
    String errorCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
    ErrorResponse errorResponse = new ErrorResponse(errorCode,
        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, WebRequest request) {
    String errorCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
    ErrorResponse errorResponse = new ErrorResponse(errorCode,
        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex, WebRequest request) {
    String errorCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
    ErrorResponse errorResponse = new ErrorResponse(errorCode,
        Collections.singletonList(ex.getMessage()), request.getDescription(false), new Date());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//    final List<String> errors = new ArrayList<>();
//    for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
//      errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
//    }
//
//    for (final ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
//      errors.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
//    }
//    ErrorResponse errorResponse = new ErrorResponse(
//        ErrorCodes.RAPO001 + " - " + HttpStatus.BAD_REQUEST, errors, request.getDescription(false),
//        new Date());
//    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//  }

//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public ResponseEntity<Map<String, List<String>>> handleValidationErrors(
//      MethodArgumentNotValidException ex) {
//    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
//        .map(FieldError::getDefaultMessage).collect(Collectors.toList());
//    return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//  }
//
//  private Map<String, List<String>> getErrorsMap(List<String> errors) {
//    Map<String, List<String>> errorResponse = new HashMap<>();
//    errorResponse.put("errors", errors);
//    return errorResponse;
//  }
}
