package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;

public class InternalServerErrorException extends CustomHttpException {
  public InternalServerErrorException(String message, Throwable cause) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
  }

  public InternalServerErrorException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }

  public InternalServerErrorException(Throwable cause) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReason(), cause);
  }

  public InternalServerErrorException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReason());
  }
}
