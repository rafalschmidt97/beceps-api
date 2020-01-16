package fi.vamk.beceps.users.api;

import fi.vamk.beceps.users.api.events.dto.UserDto;
import io.micronaut.http.annotation.Get;

public interface UsersOperations {
  @Get("/{userId}")
  UserDto get(Long userId);
}
