package fi.vamk.beceps.core.auth.api.events.commands.login;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCommand implements Command<Single<HttpResponse<AccessRefreshToken>>>, AuthenticationRequest {
  private String email;
  private String password;

  @Override
  public Object getIdentity() {
    return email;
  }

  @Override
  public Object getSecret() {
    return password;
  }
}
