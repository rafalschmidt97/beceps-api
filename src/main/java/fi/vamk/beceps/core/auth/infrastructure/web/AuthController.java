package fi.vamk.beceps.core.auth.infrastructure.web;

import fi.vamk.beceps.core.auth.api.AuthOperations;
import fi.vamk.beceps.core.auth.api.events.commands.login.LoginCommand;
import fi.vamk.beceps.core.auth.api.events.commands.logout.LogoutCommand;
import fi.vamk.beceps.core.auth.api.events.commands.logoutall.LogoutAllCommand;
import fi.vamk.beceps.core.auth.api.events.commands.refresh.RefreshCommand;
import fi.vamk.beceps.core.auth.api.events.commands.signup.SignUpCommand;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;

@Controller("/auth")
@Tag(name = "Auth")
public class AuthController extends SecuredController implements AuthOperations {
  @Override
  @Operation(summary = "Sign up with credentials.")
  public HttpResponse<AccessRefreshToken> signUp(SignUpCommand request) {
    return bus.executeCommand(request);
  }

  @Override
  @Operation(summary = "Login with credentials.")
  public Single<HttpResponse<AccessRefreshToken>> login(LoginCommand request) {
    return bus.executeCommand(request);
  }

  @Override
  @Operation(summary = "Refresh access token using refresh token.")
  public Single<HttpResponse<AccessRefreshToken>> refresh(RefreshCommand request) {
    return bus.executeCommand(request);
  }

  @Override
  @Operation(summary = "Logout using refresh token.")
  public HttpResponse logout(LogoutCommand request, Authentication authentication) {
    request.setUserId(getId(authentication));
    request.setAuthentication(authentication);
    return bus.executeCommand(request);
  }

  @Override
  @Operation(summary = "Logout from all devices.")
  public HttpResponse logoutAll(Principal principal) {
    return bus.executeCommand(new LogoutAllCommand(getId(principal)));
  }
}
