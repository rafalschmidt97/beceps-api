package fi.vamk.beceps.users.infrastructure.web;

import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.users.api.UsersOperations;
import fi.vamk.beceps.users.api.events.dto.UserDto;
import fi.vamk.beceps.users.api.events.queries.getuser.GetUserQuery;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

@Controller("/users")
@RequiredArgsConstructor
public class UsersController extends SecuredController implements UsersOperations {
  @Override
  public UserDto get(Long userId) {
    return bus.executeQuery(new GetUserQuery(userId));
  }
}
