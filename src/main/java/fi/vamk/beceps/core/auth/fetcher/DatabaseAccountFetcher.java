package fi.vamk.beceps.core.auth.fetcher;

import fi.vamk.beceps.accounts.AccountsRepository;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.reactivestreams.Publisher;

@Singleton
@RequiredArgsConstructor
public class DatabaseAccountFetcher implements UserFetcher {
  private final AccountsRepository accountsRepository;

  @Override
  public Publisher<UserState> findByUsername(String username) {
    val account = accountsRepository.findByEmail(username);

    if (!account.isPresent()) {
      return Flowable.empty();
    }

    val accountState = new DatabaseAccountState(account.get());
    return Flowable.just(accountState);
  }
}
