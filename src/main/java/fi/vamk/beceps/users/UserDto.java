package fi.vamk.beceps.users;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserDto {
  Long id;
  String email;

  UserDto(User user) {
    this(user.getId(), user.getEmail());
  }
}
