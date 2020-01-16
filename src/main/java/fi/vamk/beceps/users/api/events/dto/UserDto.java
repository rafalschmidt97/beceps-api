package fi.vamk.beceps.users.api.events.dto;

import fi.vamk.beceps.users.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String email;

  public UserDto(User user) {
    this(user.getId(), user.getEmail());
  }
}
