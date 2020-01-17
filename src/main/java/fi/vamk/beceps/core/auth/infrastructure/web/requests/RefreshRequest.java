package fi.vamk.beceps.core.auth.infrastructure.web.requests;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Introspected
public class RefreshRequest {
  @NotBlank
  private String refreshToken;
}
