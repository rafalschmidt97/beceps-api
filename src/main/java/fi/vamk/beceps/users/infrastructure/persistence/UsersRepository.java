package fi.vamk.beceps.users.infrastructure.persistence;

import fi.vamk.beceps.users.domain.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;

@Repository
public interface UsersRepository extends GenericRepository<User, Long> {
  Optional<User> findById(Long id);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  User insert(User user);
}

