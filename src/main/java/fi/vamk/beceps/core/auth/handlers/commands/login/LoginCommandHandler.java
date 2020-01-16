package fi.vamk.beceps.core.auth.handlers.commands.login;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.core.auth.api.events.commands.login.LoginCommand;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.Authenticator;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.event.LoginFailedEvent;
import io.micronaut.security.event.LoginSuccessfulEvent;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class LoginCommandHandler implements CommandHandler<Single<HttpResponse<AccessRefreshToken>>, LoginCommand> {
  private final Authenticator authenticator;
  private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public Single<HttpResponse<AccessRefreshToken>> handle(LoginCommand command) {
    return Flowable.fromPublisher(authenticator.authenticate(command))
      .map((Function<AuthenticationResponse, HttpResponse<AccessRefreshToken>>) authenticationResponse -> {
        if (authenticationResponse.isAuthenticated()) {
          val userDetails = (UserDetails) authenticationResponse;
          eventPublisher.publishEvent(new LoginSuccessfulEvent(userDetails));

          val accessRefreshTokenOptional = accessRefreshTokenGenerator.generate(userDetails);
          if (accessRefreshTokenOptional.isPresent()) {
            return HttpResponse.ok(accessRefreshTokenOptional.get());
          }
          return HttpResponse.serverError();
        } else {
          val authenticationFailed = (AuthenticationFailed) authenticationResponse;
          eventPublisher.publishEvent(new LoginFailedEvent(authenticationFailed));

          throw new AuthenticationException(authenticationFailed.getMessage().orElse(null));
        }
      }).first(HttpResponse.status(HttpStatus.UNAUTHORIZED));
  }
}
