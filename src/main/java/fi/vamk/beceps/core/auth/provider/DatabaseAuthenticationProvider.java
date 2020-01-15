package fi.vamk.beceps.core.auth.provider;

import fi.vamk.beceps.core.auth.authorieties.DatabaseAuthoritiesFetcher;
import fi.vamk.beceps.core.auth.fetcher.DatabaseUserFetcher;
import fi.vamk.beceps.core.auth.fetcher.DatabaseUserState;
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
  private final DatabaseUserFetcher userFetcher;
  private final DatabaseAuthoritiesFetcher authoritiesFetcher;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {

    return Flowable.fromPublisher(fetchUserState(authenticationRequest)).switchMap(user -> {
      if (!user.isEnabled()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_DISABLED));
      }
      if (user.isAccountExpired()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_EXPIRED));
      }
      if (user.isAccountLocked()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_LOCKED));
      }
      if (user.isPasswordExpired()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.PASSWORD_EXPIRED));
      }
      if (!passwordEncoder.matches(authenticationRequest.getSecret().toString(), user.getPassword())) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
      }
      return createSuccessfulAuthenticationResponse(user);
    }).switchIfEmpty(Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND)));
  }

  protected Publisher<DatabaseUserState> fetchUserState(AuthenticationRequest authenticationRequest) {
    val username = authenticationRequest.getIdentity().toString();
    return Flowable.fromPublisher(userFetcher.findByUsername(username))
        .switchMap(userState -> Flowable.just((DatabaseUserState) userState));
  }

  protected Publisher<AuthenticationResponse> createSuccessfulAuthenticationResponse(DatabaseUserState user) {
    return Flowable
      .fromPublisher(authoritiesFetcher.findAuthoritiesById(user.getId()))
      .map(authorities -> createSuccessfulAuthenticationResponse(user, authorities));
  }

  protected AuthenticationResponse createSuccessfulAuthenticationResponse(
      DatabaseUserState user,
      List<String> authorities
  ) {
    return new UserDetailsDetails(user.getUsername(), authorities, user.getId());
  }
}
