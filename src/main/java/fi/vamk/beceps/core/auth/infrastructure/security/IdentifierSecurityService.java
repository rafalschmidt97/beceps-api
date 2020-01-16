package fi.vamk.beceps.core.auth.infrastructure.security;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.utils.DefaultSecurityService;
import java.security.Principal;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.val;

@Singleton
@Replaces(bean = DefaultSecurityService.class)
public class IdentifierSecurityService extends DefaultSecurityService {
  public Optional<Long> identifier() {
    val subject = getAuthentication().map(Principal::getName);
    return subject.isPresent() ? subject.map(Long::parseLong) : Optional.empty();
  }

  @Override
  @Deprecated
  public Optional<String> username() {
    return super.username();
  }
}
