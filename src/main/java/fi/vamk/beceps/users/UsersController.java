package fi.vamk.beceps.users;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;

@Controller("/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class UsersController {
  private final UsersService usersService;

  @Get("/{id}")
  public UserDto findById(Long id) {
    return usersService.findById(id);
  }
}
