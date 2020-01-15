package fi.vamk.beceps.core.auth.handlers.refresh;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import lombok.Value;

@Introspected
@Value
public class AuthRefreshRequest {
  @NotBlank
  String refreshToken;
}
