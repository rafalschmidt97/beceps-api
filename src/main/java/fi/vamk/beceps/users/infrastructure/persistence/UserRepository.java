package fi.vamk.beceps.users.infrastructure.persistence;

import fi.vamk.beceps.users.domain.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@JdbcRepository(dialect = Dialect.MYSQL)
@RequiredArgsConstructor
public abstract class UserRepository implements GenericRepository<User, Long> {
  public abstract Optional<User> findById(Long id);

  public abstract Optional<User> findByEmail(String email);

  public abstract boolean existsByEmail(String email);

  public abstract boolean existsById(Long userId);

  public abstract User insert(User user);
}
