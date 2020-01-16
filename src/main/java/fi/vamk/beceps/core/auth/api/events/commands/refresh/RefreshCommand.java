package fi.vamk.beceps.core.auth.api.events.commands.refresh;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Single;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Introspected
public class RefreshCommand implements Command<Single<HttpResponse<AccessRefreshToken>>> {
  @NotBlank
  private String refreshToken;
}
