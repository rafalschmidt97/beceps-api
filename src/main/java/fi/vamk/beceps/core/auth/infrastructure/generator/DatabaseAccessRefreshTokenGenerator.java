package fi.vamk.beceps.core.auth.infrastructure.generator;

import fi.vamk.beceps.common.exceptions.InternalServerErrorException;
import fi.vamk.beceps.core.auth.domain.RefreshToken;
import fi.vamk.beceps.core.auth.infrastructure.persistence.RefreshTokenRepository;
import fi.vamk.beceps.core.auth.infrastructure.provider.DatabaseUserDetails;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.generator.TokenGenerator;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfiguration;
import io.micronaut.security.token.jwt.generator.claims.ClaimsGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.render.TokenRenderer;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.val;

@Singleton
@Replaces(bean = AccessRefreshTokenGenerator.class)
public class DatabaseAccessRefreshTokenGenerator extends AccessRefreshTokenGenerator {
  private final RefreshTokenRepository refreshTokenRepository;

  public DatabaseAccessRefreshTokenGenerator(
      JwtGeneratorConfiguration jwtGeneratorConfiguration,
      TokenRenderer tokenRenderer,
      TokenGenerator tokenGenerator,
      ClaimsGenerator claimsGenerator,
      ApplicationEventPublisher eventPublisher,
      RefreshTokenRepository refreshTokenRepository
  ) {
    super(jwtGeneratorConfiguration, tokenRenderer, tokenGenerator, claimsGenerator, eventPublisher);
    this.refreshTokenRepository = refreshTokenRepository;
  }

  // Default implementation does not add generated refresh token to the database
  @Override
  public Optional<AccessRefreshToken> generate(UserDetails userDetails) {
    if (!(userDetails instanceof DatabaseUserDetails)) {
      throw new InternalServerErrorException("Could not cast UserDetails");
    }

    val accessRefreshToken = super.generate(userDetails);

    if (accessRefreshToken.isPresent()) {
      val refreshToken = new RefreshToken(
          accessRefreshToken.get().getRefreshToken(),
          ((DatabaseUserDetails) userDetails).getId()
      );
      refreshTokenRepository.insert(refreshToken);
    }

    return accessRefreshToken;
  }
}
