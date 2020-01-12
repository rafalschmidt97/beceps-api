package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;
import lombok.Getter;

public class CustomHttpException extends RuntimeException {
  @Getter
  private final HttpStatus status;

  public CustomHttpException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public CustomHttpException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
  }
}
