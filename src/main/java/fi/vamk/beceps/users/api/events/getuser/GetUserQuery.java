package fi.vamk.beceps.users.api.events.getuser;

import fi.vamk.beceps.common.bus.query.Query;
import lombok.Value;

@Value
public class GetUserQuery implements Query<GetUserQueryResult> {
  Long userId;
}
