package fi.vamk.beceps.core.auth.api;

import fi.vamk.beceps.core.auth.api.events.commands.login.LoginCommand;
import fi.vamk.beceps.core.auth.api.events.commands.logout.LogoutCommand;
import fi.vamk.beceps.core.auth.api.events.commands.refresh.RefreshCommand;
import fi.vamk.beceps.core.auth.api.events.commands.signup.SignUpCommand;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import javax.validation.Valid;

@Tag(name = "Auth")
public interface AuthOperations {
  @Post("/sign-up")
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Operation(summary = "Sign up with credentials.")
  HttpResponse<AccessRefreshToken> signUp(@Valid @Body SignUpCommand request);

  @Post("/login")
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Operation(summary = "Login with credentials.")
  Single<HttpResponse<AccessRefreshToken>> login(@Valid @Body LoginCommand request);

  @Post("/refresh")
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Operation(summary = "Refresh access token using refresh token.")
  Single<HttpResponse<AccessRefreshToken>> refresh(@Valid @Body RefreshCommand request);

  @Post("/logout")
  @Operation(summary = "Logout using refresh token.")
  HttpResponse logout(@Valid @Body LogoutCommand request, Authentication authentication);

  @Post("/logout/all")
  @Operation(summary = "Logout from all devices.")
  HttpResponse logoutAll(Principal principal);
}



