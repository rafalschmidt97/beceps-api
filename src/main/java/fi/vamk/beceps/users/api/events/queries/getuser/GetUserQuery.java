package fi.vamk.beceps.users.api.events.queries.getuser;

import fi.vamk.beceps.common.bus.query.Query;
import fi.vamk.beceps.users.api.events.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserQuery implements Query<UserDto> {
  private Long userId;
}
