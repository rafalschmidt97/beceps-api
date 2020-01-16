package fi.vamk.beceps.users.handlers.queries.getuser;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.users.api.events.dto.UserDto;
import fi.vamk.beceps.users.api.events.queries.getuser.GetUserQuery;
import fi.vamk.beceps.users.domain.User;
import fi.vamk.beceps.users.infrastructure.persistence.UserRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class GetUserQueryHandler implements QueryHandler<UserDto, GetUserQuery> {
  private final UserRepository userRepository;

  @Override
  public UserDto handle(GetUserQuery query) {
    val user = userRepository
        .findById(query.getUserId())
        .orElseThrow(() -> new NotFoundException(User.class, query.getUserId()));

    return new UserDto(user);
  }
}
