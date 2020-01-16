package fi.vamk.beceps.users.infrastructure.web;

import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.users.api.UserOperations;
import fi.vamk.beceps.users.api.events.dto.UserDto;
import fi.vamk.beceps.users.api.events.queries.getuser.GetUserQuery;
import io.micronaut.http.annotation.Controller;

@Controller("/users")
public class UserController extends SecuredController implements UserOperations {
  @Override
  public UserDto get(Long userId) {
    return bus.executeQuery(new GetUserQuery(userId));
  }
}
