package fi.vamk.beceps.core.auth.api.events.commands.logout;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.Authentication;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Introspected
public class LogoutCommand implements Command<HttpResponse> {
  @NotBlank
  private String refreshToken;

  private Long userId;

  private Authentication authentication;
}
