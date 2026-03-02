package com.acl.exception;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ErrorResponse {

  private static final long serialVersionUID = 1L;

  private List<String> errorMessages;
  private String errorDetails;
  private Date timestamp;
  private String errorCode;

  public ErrorResponse(List<String> errorMessages) {
    this.errorMessages = errorMessages;
  }

  public ErrorResponse(List<String> errorMessages, String errorDetails, Date timestamp) {
    this.errorMessages = errorMessages;
    this.errorDetails = errorDetails;
    this.timestamp = timestamp;
  }

  public ErrorResponse(String errorCode, List<String> errorMessages, String errorDetails,
      Date timestamp) {
    this.errorCode = errorCode;
    this.errorMessages = errorMessages;
    this.errorDetails = errorDetails;
    this.timestamp = timestamp;
  }
}
