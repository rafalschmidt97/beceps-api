package fi.vamk.beceps.common.exceptions;

import io.micronaut.http.HttpStatus;

public class ConflictException extends CustomHttpException {
  private static final long serialVersionUID = -8026803611985771016L;

  public ConflictException(String message) {
    super(HttpStatus.CONFLICT, message);
  }

  public static ConflictException alreadyExists(Class entity, Object key) {
    return new ConflictException(String.format("%s(%s) already exists.", entity.getSimpleName(), key));
  }
}
