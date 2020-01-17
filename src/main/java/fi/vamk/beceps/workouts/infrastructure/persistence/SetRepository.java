package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.workouts.domain.Set;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;

@Repository
public interface SetRepository extends GenericRepository<Set, Long> {
  Optional<Set> findById(Long id);

  boolean existsById(Long id);

  Set insert(Set set);

  void delete(Set set);
}
