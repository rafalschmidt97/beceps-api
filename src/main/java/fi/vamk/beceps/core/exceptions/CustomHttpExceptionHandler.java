package fi.vamk.beceps.core.exceptions;

import fi.vamk.beceps.common.exceptions.CustomHttpException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import java.util.HashMap;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Singleton
@Slf4j
public class CustomHttpExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, Exception exception) {
    val response = new HashMap<String, Object>();

    if (exception instanceof CustomHttpException) {
      val customException = (CustomHttpException) exception;
      val message = String.format(
          "%s: %s",
          customException.getStatus().getReason(),
          customException.getMessage()
      );

      log.error(message);
      response.put("message", message);
      return HttpResponse.status(customException.getStatus()).body(response);
    } else {
      log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReason(), exception);
      response.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReason());
      return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }
}
