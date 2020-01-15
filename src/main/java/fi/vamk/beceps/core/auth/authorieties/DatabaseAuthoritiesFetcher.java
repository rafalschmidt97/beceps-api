package fi.vamk.beceps.core.auth.authorieties;

import io.reactivex.Flowable;
import java.util.Collections;
import java.util.List;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class DatabaseAuthoritiesFetcher {
  // Instead of searching by username as in AuthoritiesFetcher it uses id
  public Publisher<List<String>> findAuthoritiesById(Long id) {
    return Flowable.just(Collections.emptyList());
  }
}
