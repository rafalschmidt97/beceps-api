package fi.vamk.beceps.core.auth.handlers.commands.logoutall;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.core.auth.api.events.commands.logoutall.LogoutAllCommand;
import fi.vamk.beceps.core.auth.infrastructure.persistence.RefreshTokensRepository;
import io.micronaut.http.HttpResponse;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class LogoutAllCommandHandler implements CommandHandler<HttpResponse, LogoutAllCommand> {
  private final RefreshTokensRepository refreshTokensRepository;

  @Override
  public HttpResponse handle(LogoutAllCommand command) {
    refreshTokensRepository.deleteAllByUserId(command.getUserId());
    return HttpResponse.ok();
  }
}
