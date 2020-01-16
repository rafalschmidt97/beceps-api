package fi.vamk.beceps.users.api;

import fi.vamk.beceps.users.api.events.dto.UserDto;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users")
public interface UserOperations {
  @Get("/{userId}")
  @Operation(summary = "Get user using his identifier.")
  UserDto get(Long userId);
}
