package fi.vamk.beceps.core.exceptions;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;

public class ErrorResponse extends JsonError {
  public ErrorResponse(String message, HttpRequest request) {
    super(message);
    link(Link.SELF, Link.of(request.getUri()));
  }
}
