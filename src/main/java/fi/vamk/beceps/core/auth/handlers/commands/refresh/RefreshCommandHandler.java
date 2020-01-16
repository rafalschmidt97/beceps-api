package fi.vamk.beceps.core.auth.handlers.commands.refresh;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.UnauthorizedException;
import fi.vamk.beceps.core.auth.api.events.commands.refresh.RefreshCommand;
import fi.vamk.beceps.core.auth.infrastructure.persistence.RefreshTokensRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.validator.TokenValidator;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RefreshCommandHandler implements CommandHandler<Single<HttpResponse<AccessRefreshToken>>, RefreshCommand> {
  private final TokenValidator tokenValidator;
  private final RefreshTokensRepository refreshTokensRepository;
  private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;

  @Override
  public Single<HttpResponse<AccessRefreshToken>> handle(RefreshCommand command) {
    return Flowable.fromPublisher(tokenValidator.validateToken(command.getRefreshToken()))
      .map((Function<Authentication, HttpResponse<AccessRefreshToken>>) authentication -> {
        if (!refreshTokensRepository.existsByToken(command.getRefreshToken())) {
          throw new UnauthorizedException();
        }

        val claims = authentication.getAttributes();
        val accessRefreshToken = accessRefreshTokenGenerator.generate(command.getRefreshToken(), claims);

        if (accessRefreshToken.isPresent()) {
          return HttpResponse.ok(accessRefreshToken.get());
        }
        return HttpResponse.serverError();
      }).first(HttpResponse.status(HttpStatus.FORBIDDEN));
  }
}
