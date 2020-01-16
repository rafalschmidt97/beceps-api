package fi.vamk.beceps.core.auth.infrastructure.web;

import fi.vamk.beceps.common.bus.command.CommandBus;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import java.security.Principal;
import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
public class SecuredController {
  @Inject
  protected CommandBus bus;

  protected Long getId(Principal principal) {
    return Long.parseLong(principal.getName());
  }
}
