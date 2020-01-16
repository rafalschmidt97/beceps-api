package fi.vamk.beceps.users.infrastructure.web;

import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.users.api.UserOperations;
import fi.vamk.beceps.users.api.events.dto.UserDto;
import fi.vamk.beceps.users.api.events.queries.getuser.GetUserQuery;
import io.micronaut.http.annotation.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller("/users")
@Tag(name = "Users")
public class UserController extends SecuredController implements UserOperations {
  @Override
  @Operation(summary = "Get user using his identifier.")
  public UserDto get(Long userId) {
    return bus.executeQuery(new GetUserQuery(userId));
  }
}
