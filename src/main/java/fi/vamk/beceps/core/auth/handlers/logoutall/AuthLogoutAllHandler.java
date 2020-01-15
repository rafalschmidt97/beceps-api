package fi.vamk.beceps.core.auth.handlers.logoutall;

import fi.vamk.beceps.core.auth.refresh.RefreshTokensRepository;
import io.micronaut.http.HttpResponse;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class AuthLogoutAllHandler {
  private final RefreshTokensRepository refreshTokensRepository;

  public HttpResponse handle(Long userId) {
    refreshTokensRepository.deleteAllByUserId(userId);
    return HttpResponse.ok();
  }
}
