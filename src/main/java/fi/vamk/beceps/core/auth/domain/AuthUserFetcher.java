package fi.vamk.beceps.core.auth.domain;

import fi.vamk.beceps.users.infrastructure.persistence.UserRepository;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.reactivestreams.Publisher;

@Singleton
@RequiredArgsConstructor
public class AuthUserFetcher implements UserFetcher {
  private final UserRepository userRepository;

  @Override
  public Publisher<UserState> findByUsername(String username) {
    val user = userRepository.findByEmail(username);

    if (!user.isPresent()) {
      return Flowable.empty();
    }

    val userState = new AuthUserState(user.get());
    return Flowable.just(userState);
  }
}
