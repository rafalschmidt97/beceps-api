package fi.vamk.beceps.core.auth.api.events.commands.refresh;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshCommand implements Command<Single<HttpResponse<AccessRefreshToken>>> {
  private String refreshToken;
}
