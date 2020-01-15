package fi.vamk.beceps.core.auth.security;

import io.micronaut.security.utils.SecurityService;
import java.util.Optional;

public interface DatabaseSecurityService extends SecurityService {
  Optional<Long> identifier();
}


