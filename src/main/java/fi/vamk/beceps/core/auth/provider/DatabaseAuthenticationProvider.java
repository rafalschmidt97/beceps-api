package fi.vamk.beceps.core.auth.provider;

import fi.vamk.beceps.core.auth.authorieties.DatabaseAuthoritiesFetcher;
import fi.vamk.beceps.core.auth.fetcher.DatabaseAccountFetcher;
import fi.vamk.beceps.core.auth.fetcher.DatabaseAccountState;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.providers.DelegatingAuthenticationProvider;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.reactivestreams.Publisher;

@Singleton
@RequiredArgsConstructor
@Replaces(bean = DelegatingAuthenticationProvider.class)
public class DatabaseAuthenticationProvider implements AuthenticationProvider {
  private final DatabaseAccountFetcher userFetcher;
  private final DatabaseAuthoritiesFetcher authoritiesFetcher;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {

    return Flowable.fromPublisher(fetchAccountState(authenticationRequest)).switchMap(account -> {
      if (!account.isEnabled()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_DISABLED));
      }
      if (account.isAccountExpired()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_EXPIRED));
      }
      if (account.isAccountLocked()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_LOCKED));
      }
      if (account.isPasswordExpired()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.PASSWORD_EXPIRED));
      }
      if (!passwordEncoder.matches(authenticationRequest.getSecret().toString(), account.getPassword())) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
      }
      return createSuccessfulAuthenticationResponse(account);
    }).switchIfEmpty(Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND)));
  }

  protected Publisher<DatabaseAccountState> fetchAccountState(AuthenticationRequest authenticationRequest) {
    val username = authenticationRequest.getIdentity().toString();
    return Flowable.fromPublisher(userFetcher.findByUsername(username))
        .switchMap(userState -> Flowable.just((DatabaseAccountState) userState));
  }

  protected Publisher<AuthenticationResponse> createSuccessfulAuthenticationResponse(DatabaseAccountState account) {
    return Flowable
      .fromPublisher(authoritiesFetcher.findAuthoritiesById(account.getId()))
      .map(authorities -> createSuccessfulAuthenticationResponse(account, authorities));
  }

  protected AuthenticationResponse createSuccessfulAuthenticationResponse(
      DatabaseAccountState account,
      List<String> authorities
  ) {
    return new DatabaseAccountDetails(account.getUsername(), authorities, account.getId());
  }
}
