package fi.vamk.beceps.core.auth.api.events.commands.logout;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.Authentication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutCommand implements Command<HttpResponse> {
  private String refreshToken;
  private Long userId;
  private Authentication authentication;
}
