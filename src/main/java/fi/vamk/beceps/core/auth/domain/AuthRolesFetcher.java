package fi.vamk.beceps.core.auth.domain;

import io.reactivex.Flowable;
import java.util.Collections;
import java.util.List;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class AuthRolesFetcher {
  // Instead of searching by username as in AuthoritiesFetcher it uses id
  public Publisher<List<String>> findRolesByUserId(Long userId) {
    return Flowable.just(Collections.emptyList());
  }
}
