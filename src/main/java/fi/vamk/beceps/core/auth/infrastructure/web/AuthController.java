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
import java.security.Principal;

@Controller("/auth")
public class AuthController extends SecuredController implements AuthOperations {
  @Override
  public HttpResponse<AccessRefreshToken> signUp(SignUpCommand request) {
    return bus.executeCommand(request);
  }

  @Override
  public Single<HttpResponse<AccessRefreshToken>> login(LoginCommand request) {
    return bus.executeCommand(request);
  }

  @Override
  public Single<HttpResponse<AccessRefreshToken>> refresh(RefreshCommand request) {
    return bus.executeCommand(request);
  }

  @Override
  public HttpResponse logout(LogoutCommand request, Authentication authentication) {
    request.setUserId(getId(authentication));
    request.setAuthentication(authentication);
    return bus.executeCommand(request);
  }

  @Override
  public HttpResponse logoutAll(Principal principal) {
    return bus.executeCommand(new LogoutAllCommand(getId(principal)));
  }
}
