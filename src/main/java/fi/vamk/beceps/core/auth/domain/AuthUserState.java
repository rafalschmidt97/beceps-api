package fi.vamk.beceps.core.auth.domain;

import fi.vamk.beceps.users.domain.User;
import io.micronaut.security.authentication.providers.UserState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class AuthUserState implements UserState {
  @Getter
  private final Long id;
  private final String email;
  private final String password;
  private final boolean isLocked;

  public AuthUserState(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.isLocked = user.isLocked();
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isAccountExpired() {
    return false;
  }

  @Override
  public boolean isAccountLocked() {
    return isLocked;
  }

  @Override
  public boolean isPasswordExpired() {
    return false;
  }
}
