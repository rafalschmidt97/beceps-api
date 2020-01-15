package fi.vamk.beceps.core.exceptions;

import fi.vamk.beceps.common.exceptions.CustomHttpException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Singleton
@Slf4j
public class CustomHttpExceptionHandler implements ExceptionHandler<Exception, HttpResponse> {
  @Override
  public HttpResponse handle(HttpRequest request, Exception exception) {
    if (exception instanceof CustomHttpException) {
      val customException = (CustomHttpException) exception;
      val message = String.format(
          "%s: %s",
          customException.getStatus().getReason(),
          customException.getMessage()
      );

      log.error(message);
      return HttpResponse.status(customException.getStatus()).body(new ErrorResponse(message, request));
    } else {
      log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReason(), exception);
      return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
