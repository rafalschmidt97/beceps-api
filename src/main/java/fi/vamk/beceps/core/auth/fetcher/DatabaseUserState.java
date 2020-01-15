package fi.vamk.beceps.core.auth.fetcher;

import fi.vamk.beceps.users.User;
import io.micronaut.security.authentication.providers.UserState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DatabaseUserState implements UserState {
  @Getter
  private final Long id;
  private final String email;
  private final String password;

  public DatabaseUserState(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
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
    return false;
  }

  @Override
  public boolean isPasswordExpired() {
    return false;
  }
}
