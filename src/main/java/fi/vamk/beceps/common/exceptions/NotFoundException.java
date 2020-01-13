package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;

public class NotFoundException extends CustomHttpException {
  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }

  public NotFoundException(Class entity, Object key) {
    this(String.format("%s(%s) could not be found.", entity.getSimpleName(), key));
  }
}
