package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.workouts.domain.Routine;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;

@Repository
public interface RoutineRepository extends GenericRepository<Routine, Long> {
  Optional<Routine> findById(Long id);

  Optional<Routine> findByWorkoutIdAndWeekDay(Long workoutId, int weekDay);

  Routine save(Routine routine);

  Routine insert(Routine routine);

  void deleteById(Long id);
}
