package fi.vamk.beceps.core.auth;

import fi.vamk.beceps.core.auth.handlers.login.AuthLoginHandler;
import fi.vamk.beceps.core.auth.handlers.logout.AuthLogoutHandler;
import fi.vamk.beceps.core.auth.handlers.logout.AuthLogoutRequest;
import fi.vamk.beceps.core.auth.handlers.logoutall.AuthLogoutAllHandler;
import fi.vamk.beceps.core.auth.handlers.refresh.AuthRefreshHandler;
import fi.vamk.beceps.core.auth.handlers.refresh.AuthRefreshRequest;
import fi.vamk.beceps.core.auth.handlers.signup.AuthSignUpHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AuthController {
  private final AuthRefreshHandler refreshHandler;
  private final AuthLogoutAllHandler logoutAllHandler;
  private final AuthSignUpHandler signUpHandler;
  private final AuthLogoutHandler logoutHandler;
  private final AuthLoginHandler loginHandler;

  @Post("/sign-up")
  @Secured(SecurityRule.IS_ANONYMOUS)
  public HttpResponse<AccessRefreshToken> signUp(@Valid @Body UsernamePasswordCredentials request) {
    return signUpHandler.handle(request);
  }

  @Post("/login")
  @Secured(SecurityRule.IS_ANONYMOUS)
  public Single<HttpResponse<AccessRefreshToken>> login(@Valid @Body UsernamePasswordCredentials request) {
    return loginHandler.handle(request);
  }

  @Post("/logout")
  @Secured(SecurityRule.IS_AUTHENTICATED)
  public HttpResponse logout(@Valid @Body AuthLogoutRequest request, Authentication authentication) {
    return logoutHandler.handle(request, authentication);
  }

  @Post("/refresh")
  @Secured(SecurityRule.IS_ANONYMOUS)
  public Single<HttpResponse<AccessRefreshToken>> refresh(@Valid @Body AuthRefreshRequest request) {
    return refreshHandler.handle(request);
  }

  @Post("/logout/all")
  public HttpResponse logoutAll(Principal principal) {
    return logoutAllHandler.handle(getId(principal));
  }

  private Long getId(Principal principal) {
    return Long.parseLong(principal.getName());
  }
}
