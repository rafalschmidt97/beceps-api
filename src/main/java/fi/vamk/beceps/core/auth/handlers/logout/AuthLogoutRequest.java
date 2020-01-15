package fi.vamk.beceps.core.auth.handlers.logout;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class AuthLogoutRequest {
  @NotBlank
  String refreshToken;
}
