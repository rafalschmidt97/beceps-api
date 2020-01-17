package fi.vamk.beceps.core.auth.infrastructure.web;

import fi.vamk.beceps.core.auth.api.AuthOperations;
import fi.vamk.beceps.core.auth.api.events.commands.login.LoginCommand;
import fi.vamk.beceps.core.auth.api.events.commands.logout.LogoutCommand;
import fi.vamk.beceps.core.auth.api.events.commands.logoutall.LogoutAllCommand;
import fi.vamk.beceps.core.auth.api.events.commands.refresh.RefreshCommand;
import fi.vamk.beceps.core.auth.api.events.commands.signup.SignUpCommand;
import fi.vamk.beceps.core.auth.infrastructure.web.requests.LoginRequest;
import fi.vamk.beceps.core.auth.infrastructure.web.requests.LogoutRequest;
import fi.vamk.beceps.core.auth.infrastructure.web.requests.RefreshRequest;
import fi.vamk.beceps.core.auth.infrastructure.web.requests.SignUpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Single;
import java.security.Principal;

@Controller("/auth")
public class AuthController extends SecuredController implements AuthOperations {
  @Override
  public HttpResponse<AccessRefreshToken> signUp(SignUpRequest request) {
    return bus.executeCommand(new SignUpCommand(request.getEmail(), request.getPassword()));
  }

  @Override
  public Single<HttpResponse<AccessRefreshToken>> login(LoginRequest request) {
    return bus.executeCommand(new LoginCommand(request.getEmail(), request.getPassword()));
  }

  @Override
  public Single<HttpResponse<AccessRefreshToken>> refresh(RefreshRequest request) {
    return bus.executeCommand(new RefreshCommand(request.getRefreshToken()));
  }

  @Override
  public HttpResponse logout(LogoutRequest request, Authentication authentication) {
    return bus.executeCommand(new LogoutCommand(request.getRefreshToken(), getId(authentication), authentication));
  }

  @Override
  public HttpResponse logoutAll(Principal principal) {
    return bus.executeCommand(new LogoutAllCommand(getId(principal)));
  }
}
