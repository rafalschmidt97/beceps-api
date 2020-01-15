package fi.vamk.beceps.core.auth.handlers.refresh;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class AuthRefreshRequest {
  @NotBlank
  String refreshToken;
}
