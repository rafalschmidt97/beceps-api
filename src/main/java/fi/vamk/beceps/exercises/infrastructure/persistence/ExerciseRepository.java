package fi.vamk.beceps.exercises.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.exercises.infrastructure.persistence.dao.ExerciseWithUser;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@JdbcRepository(dialect = Dialect.MYSQL)
@RequiredArgsConstructor
public abstract class ExerciseRepository implements GenericRepository<Exercise, Long> {
  private final JdbcFetcher jdbcFetcher;

  public abstract Optional<Exercise> findById(Long id);

  @Transactional
  public Optional<ExerciseWithUser> findWithUserIdById(Long id) {
    val query = "select exercise.id as id, workout.user_id as user_id from exercise " +
                "inner join `set` on exercise.set_id = `set`.id " +
                "inner join routine on `set`.routine_id = routine.id " +
                "inner join workout on routine.workout_id = workout.id " +
                "where exercise.id = ? " +
                "limit 1";

    return jdbcFetcher.fetch(query, statement -> {
      statement.setLong(1, id);
    }, record -> new ExerciseWithUser(
      record.getLong("id"),
      record.getLong("user_id")
    )).stream().findFirst();
  }

  public abstract List<Exercise> findAllByUserIdAndCreatedAtAfter(Long userId, Instant createdAfter);

  public abstract List<Exercise> findAllByUserIdAndCreatedAtBetween(
      Long userId,
      Instant createdAfter,
      Instant createdBefore
  );

  public abstract Exercise insert(Exercise exercise);

  public abstract void deleteById(Long id);
}
