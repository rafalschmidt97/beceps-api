package fi.vamk.beceps.exercises.infrastructure.persistence;

import fi.vamk.beceps.exercises.domain.Exercise;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends GenericRepository<Exercise, Long> {
  Optional<Exercise> findById(Long id);

  List<Exercise> findAllByUserIdAndCreatedAtAfter(Long userId, Date createdAfter);

  List<Exercise> findAllByUserIdAndCreatedAtBetween(Long userId, Date createdAfter, Date createdBefore);

  Exercise insert(Exercise exercise);

  void deleteById(Long id);
}

