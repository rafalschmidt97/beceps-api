package fi.vamk.beceps.core.auth.fetcher;

import fi.vamk.beceps.users.domain.UsersRepository;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.reactivestreams.Publisher;

@Singleton
@RequiredArgsConstructor
public class DatabaseUserFetcher implements UserFetcher {
  private final UsersRepository usersRepository;

  @Override
  public Publisher<UserState> findByUsername(String username) {
    val user = usersRepository.findByEmail(username);

    if (!user.isPresent()) {
      return Flowable.empty();
    }

    val userState = new DatabaseUserState(user.get());
    return Flowable.just(userState);
  }
}
