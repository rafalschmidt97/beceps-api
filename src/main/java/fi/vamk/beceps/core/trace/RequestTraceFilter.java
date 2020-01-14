package fi.vamk.beceps.core.trace;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Filter("/**")
@Slf4j
public class RequestTraceFilter extends OncePerRequestHttpServerFilter {
  @Override
  protected Publisher<MutableHttpResponse<?>> doFilterOnce(HttpRequest<?> request, ServerFilterChain chain) {
    return Flowable.fromCallable(() -> {
      log.info(
          "Request: {} {} {}",
          request.getMethod(),
          request.getUri(),
          request.getBody().isPresent() ? request.getBody() : ""
      );

      return true;
    }).subscribeOn(Schedulers.io())
      .switchMap(call -> chain.proceed(request))
      .doOnNext(res ->
        res.getHeaders().add("X-Trace-Enabled", "true")
      );
  }
}
