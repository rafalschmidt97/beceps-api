package fi.vamk.beceps.core.auth.infrastructure.generator;

import com.nimbusds.jwt.JWTClaimsSet;
import fi.vamk.beceps.common.exceptions.InternalServerErrorException;
import fi.vamk.beceps.core.auth.infrastructure.provider.DatabaseUserDetails;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.security.token.jwt.generator.claims.ClaimsAudienceProvider;
import io.micronaut.security.token.jwt.generator.claims.JWTClaimsSetGenerator;
import io.micronaut.security.token.jwt.generator.claims.JwtIdGenerator;
import javax.annotation.Nullable;
import javax.inject.Singleton;

@Singleton
@Replaces(bean = JWTClaimsSetGenerator.class)
public class DatabaseClaimsSetGenerator extends JWTClaimsSetGenerator {
  public DatabaseClaimsSetGenerator(
      TokenConfiguration tokenConfiguration,
      @Nullable JwtIdGenerator jwtIdGenerator,
      @Nullable ClaimsAudienceProvider claimsAudienceProvider,
      @Nullable ApplicationConfiguration applicationConfiguration
  ) {
    super(tokenConfiguration, jwtIdGenerator, claimsAudienceProvider, applicationConfiguration);
  }

  @Override
  protected void populateSub(JWTClaimsSet.Builder builder, UserDetails userDetails) {
    if (!(userDetails instanceof DatabaseUserDetails)) {
      throw new InternalServerErrorException("Could not cast UserDetails");
    }

    builder.subject(((DatabaseUserDetails) userDetails).getId().toString());
  }
}
