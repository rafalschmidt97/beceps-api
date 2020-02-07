package fi.vamk.beceps.users.infrastructure.persistence;

import fi.vamk.beceps.users.domain.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface UserRepository extends GenericRepository<User, Long> {
  Optional<User> findById(Long id);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  boolean existsById(Long userId);

  User insert(User user);
}
