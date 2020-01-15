package fi.vamk.beceps.users;

import fi.vamk.beceps.common.exceptions.NotFoundException;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class UsersService {
  private final UsersRepository usersRepository;

  public UserDto findById(Long id) {
    val user = usersRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(User.class, id));

    return new UserDto(user);
  }
}
