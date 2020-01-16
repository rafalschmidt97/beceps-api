package fi.vamk.beceps.core.auth.infrastructure.provider;

import fi.vamk.beceps.core.auth.domain.AuthRolesFetcher;
import fi.vamk.beceps.core.auth.domain.AuthUserFetcher;
import fi.vamk.beceps.core.auth.domain.AuthUserState;
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
public class AuthProvider implements AuthenticationProvider {
  private final AuthUserFetcher userFetcher;
  private final AuthRolesFetcher authoritiesFetcher;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {

    return Flowable.fromPublisher(fetchUserState(authenticationRequest)).switchMap(user -> {
      if (user.isAccountLocked()) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_LOCKED));
      }
      if (!passwordEncoder.matches(authenticationRequest.getSecret().toString(), user.getPassword())) {
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
      }

      return createSuccessfulAuthenticationResponse(user);
    }).switchIfEmpty(Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND)));
  }

  protected Publisher<AuthUserState> fetchUserState(AuthenticationRequest authenticationRequest) {
    val username = authenticationRequest.getIdentity().toString();
    return Flowable.fromPublisher(userFetcher.findByUsername(username))
        .switchMap(userState -> Flowable.just((AuthUserState) userState));
  }

  protected Publisher<AuthenticationResponse> createSuccessfulAuthenticationResponse(AuthUserState user) {
    return Flowable
      .fromPublisher(authoritiesFetcher.findRolesByUserId(user.getId()))
      .map(authorities -> createSuccessfulAuthenticationResponse(user, authorities));
  }

  protected AuthenticationResponse createSuccessfulAuthenticationResponse(
      AuthUserState user,
      List<String> authorities
  ) {
    return new AuthUserDetails(user.getUsername(), authorities, user.getId());
  }
}
