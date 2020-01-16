package fi.vamk.beceps.core.auth.api.events.commands.logoutall;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutAllCommand implements Command<HttpResponse> {
  private Long userId;
}
