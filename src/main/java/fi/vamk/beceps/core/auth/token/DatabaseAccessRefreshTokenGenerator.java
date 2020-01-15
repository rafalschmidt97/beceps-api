package fi.vamk.beceps.core.auth.token;

import fi.vamk.beceps.common.exceptions.InternalServerErrorException;
import fi.vamk.beceps.core.auth.provider.UserDetailsDetails;
import fi.vamk.beceps.core.auth.refresh.RefreshToken;
import fi.vamk.beceps.core.auth.refresh.RefreshTokensRepository;
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
  private final RefreshTokensRepository refreshTokensRepository;

  public DatabaseAccessRefreshTokenGenerator(
      JwtGeneratorConfiguration jwtGeneratorConfiguration,
      TokenRenderer tokenRenderer,
      TokenGenerator tokenGenerator,
      ClaimsGenerator claimsGenerator,
      ApplicationEventPublisher eventPublisher,
      RefreshTokensRepository refreshTokensRepository
  ) {
    super(jwtGeneratorConfiguration, tokenRenderer, tokenGenerator, claimsGenerator, eventPublisher);
    this.refreshTokensRepository = refreshTokensRepository;
  }

  // Default implementation does not add generated refresh token to the database
  @Override
  public Optional<AccessRefreshToken> generate(UserDetails userDetails) {
    if (!(userDetails instanceof UserDetailsDetails)) {
      throw new InternalServerErrorException("Could not cast UserDetails");
    }

    val accessRefreshToken = super.generate(userDetails);

    if (accessRefreshToken.isPresent()) {
      val refreshToken = new RefreshToken(
          accessRefreshToken.get().getRefreshToken(),
          ((UserDetailsDetails) userDetails).getId()
      );
      refreshTokensRepository.save(refreshToken);
    }

    return accessRefreshToken;
  }
}
