package fi.vamk.beceps.core.auth.api.events.commands.signup;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpCommand implements Command<HttpResponse<AccessRefreshToken>> {
  private String email;
  private String password;
}
