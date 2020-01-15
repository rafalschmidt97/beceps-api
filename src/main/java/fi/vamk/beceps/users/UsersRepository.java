package fi.vamk.beceps.users;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}

