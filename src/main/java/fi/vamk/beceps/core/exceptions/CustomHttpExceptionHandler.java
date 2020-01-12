package fi.vamk.beceps.core.exceptions;

import fi.vamk.beceps.common.exceptions.CustomHttpException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import java.util.HashMap;
import javax.inject.Singleton;
import lombok.val;

@Singleton
public class CustomHttpExceptionHandler implements ExceptionHandler<CustomHttpException, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, CustomHttpException exception) {
    val response = new HashMap<String, Object>();
    response.put("message", exception.getMessage());
    return HttpResponse.status(exception.getStatus()).body(response);
  }
}
