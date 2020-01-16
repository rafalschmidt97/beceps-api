package fi.vamk.beceps.core.auth.handlers.commands.logout;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.core.auth.api.events.commands.logout.LogoutCommand;
import fi.vamk.beceps.core.auth.infrastructure.persistence.RefreshTokensRepository;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.event.LogoutEvent;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class LogoutCommandHandler implements CommandHandler<HttpResponse, LogoutCommand> {
  private final RefreshTokensRepository refreshTokensRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public HttpResponse handle(LogoutCommand command) {
    refreshTokensRepository.deleteByToken(command.getRefreshToken());
    eventPublisher.publishEvent(new LogoutEvent(command.getAuthentication()));
    return HttpResponse.ok();
  }
}
