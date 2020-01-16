package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.workouts.domain.Workout;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends GenericRepository<Workout, Long> {
  List<Workout> findAllByUserId(Long userId);

  Optional<Workout> findById(Long id);

  boolean existsById(Long id);

  Workout insert(Workout workout);

  Workout save(Workout workout);

  void deleteById(Long id);

  void delete(Workout workout);
}
