package fi.vamk.beceps.core.auth.fetcher;

import fi.vamk.beceps.accounts.Account;
import io.micronaut.security.authentication.providers.UserState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class DatabaseAccountState implements UserState {
  @Getter
  private final Long id;
  private final String email;
  private final String password;

  public DatabaseAccountState(Account account) {
    this.id = account.getId();
    this.email = account.getEmail();
    this.password = account.getPassword();
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
