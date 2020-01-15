package fi.vamk.beceps.core.auth.encoder;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import javax.inject.Singleton;

@Singleton
public class BCryptPasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(String rawPassword) {
    return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
  }

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword).verified;
  }
}
