package fi.vamk.beceps.core.auth.provider;

import fi.vamk.beceps.accounts.Account;
import io.micronaut.security.authentication.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
public class DatabaseAccountDetails extends UserDetails {
  @Getter
  @Setter
  private Long id;

  public DatabaseAccountDetails(String username, Collection<String> roles, Long id) {
    super(username, roles);
    this.id = id;
  }

  public DatabaseAccountDetails(Account account) {
    this(account.getEmail(), Collections.emptyList(), account.getId());
  }

  @Override
  public Map<String, Object> getAttributes(String rolesKey, String subjectKey) {
    val result = new HashMap<String, Object>();
    result.putIfAbsent(rolesKey, getRoles());
    result.putIfAbsent(subjectKey, getId().toString());
    return result;
  }
}
