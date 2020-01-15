package fi.vamk.beceps.users.api.events.dto;

import fi.vamk.beceps.users.domain.User;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserDto {
  Long id;
  String email;

  public UserDto(User user) {
    this(user.getId(), user.getEmail());
  }
}
