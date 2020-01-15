package fi.vamk.beceps.users.infrastructure.web;

import fi.vamk.beceps.common.bus.command.CommandBus;
import fi.vamk.beceps.users.api.UsersOperations;
import fi.vamk.beceps.users.api.events.getuser.GetUserQuery;
import fi.vamk.beceps.users.api.events.getuser.GetUserQueryResult;
import io.micronaut.http.annotation.Controller;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

@Controller("/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class UsersController implements UsersOperations {
  private final CommandBus bus;

  @Override
  public GetUserQueryResult get(Long userId) {
    return bus.executeQuery(new GetUserQuery(userId));
  }
}
