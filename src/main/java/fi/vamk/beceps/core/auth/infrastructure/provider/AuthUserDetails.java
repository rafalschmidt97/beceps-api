package fi.vamk.beceps.core.auth.infrastructure.provider;

import fi.vamk.beceps.users.domain.User;
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
public class AuthUserDetails extends UserDetails {
  @Getter
  @Setter
  private Long id;

  public AuthUserDetails(String username, Collection<String> roles, Long id) {
    super(username, roles);
    this.id = id;
  }

  public AuthUserDetails(User user) {
    this(user.getEmail(), Collections.emptyList(), user.getId());
  }

  @Override
  public Map<String, Object> getAttributes(String rolesKey, String subjectKey) {
    val result = new HashMap<String, Object>();
    result.putIfAbsent(rolesKey, getRoles());
    result.putIfAbsent(subjectKey, getId().toString());
    return result;
  }
}
