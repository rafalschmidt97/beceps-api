package fi.vamk.beceps.core.auth.handlers.logout;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import lombok.Value;

@Introspected
@Value
public class AuthLogoutRequest {
  @NotBlank
  String refreshToken;
}
