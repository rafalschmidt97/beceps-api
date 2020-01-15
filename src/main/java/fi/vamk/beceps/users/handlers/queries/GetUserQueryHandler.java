package fi.vamk.beceps.users.handlers.queries;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.users.api.events.dto.UserDto;
import fi.vamk.beceps.users.api.events.getuser.GetUserQuery;
import fi.vamk.beceps.users.api.events.getuser.GetUserQueryResult;
import fi.vamk.beceps.users.domain.User;
import fi.vamk.beceps.users.domain.UsersRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class GetUserQueryHandler implements QueryHandler<GetUserQueryResult, GetUserQuery> {
  private final UsersRepository usersRepository;

  @Override
  public GetUserQueryResult handle(GetUserQuery query) {
    val user = usersRepository
        .findById(query.getUserId())
        .orElseThrow(() -> new NotFoundException(User.class, query.getUserId()));

    return new GetUserQueryResult(new UserDto(user));
  }
}
