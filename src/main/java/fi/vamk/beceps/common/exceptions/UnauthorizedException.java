package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;

public class UnauthorizedException extends CustomHttpException {
  public UnauthorizedException(String message) {
    super(HttpStatus.UNAUTHORIZED, message);
  }

  public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReason());
  }
}
