package fi.vamk.beceps.users.api.events.getuser;

import fi.vamk.beceps.users.api.events.dto.UserDto;
import lombok.Value;

@Value
public class GetUserQueryResult {
  UserDto user;
}
