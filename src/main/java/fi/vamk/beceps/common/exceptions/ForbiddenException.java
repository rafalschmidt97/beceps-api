package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;

public class ForbiddenException extends CustomHttpException {
  public ForbiddenException(String message) {
    super(HttpStatus.FORBIDDEN, message);
  }

  public ForbiddenException() {
    super(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReason());
  }
}
