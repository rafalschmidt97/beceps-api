package fi.vamk.beceps.users.api;

import fi.vamk.beceps.users.api.events.getuser.GetUserQueryResult;
import io.micronaut.http.annotation.Get;

public interface UsersOperations {
  @Get("/{userId}")
  GetUserQueryResult get(Long userId);
}
