package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;

public class BadRequestException extends CustomHttpException {
  public BadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }

  public BadRequestException() {
    super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReason());
  }
}
