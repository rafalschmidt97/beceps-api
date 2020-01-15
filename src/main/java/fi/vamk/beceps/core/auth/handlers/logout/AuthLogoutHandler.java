package fi.vamk.beceps.core.auth.handlers.logout;

import fi.vamk.beceps.core.auth.refresh.RefreshTokensRepository;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.event.LogoutEvent;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class AuthLogoutHandler {
  private final RefreshTokensRepository refreshTokensRepository;
  private final ApplicationEventPublisher eventPublisher;

  public HttpResponse handle(AuthLogoutRequest request, Authentication authentication) {
    refreshTokensRepository.deleteByToken(request.getRefreshToken());
    eventPublisher.publishEvent(new LogoutEvent(authentication));
    return HttpResponse.ok();
  }
}
