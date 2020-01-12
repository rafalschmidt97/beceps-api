package fi.vamk.beceps.core.trace;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Filter("/**")
@Slf4j
public class RequestTraceFilter implements HttpServerFilter {
  @Override
  public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
    return Flowable.fromCallable(() -> {
      if (!request.getPath().equals("/favicon.ico")) {
        log.info(
            "Request: {} {} {}",
            request.getMethod(),
            request.getUri(),
            request.getBody().isPresent() ? request.getBody() : ""
        );
      }

      return true;
    }).subscribeOn(Schedulers.io())
      .switchMap(call -> chain.proceed(request))
      .doOnNext(res ->
        res.getHeaders().add("X-Trace-Enabled", "true")
      );
  }
}
