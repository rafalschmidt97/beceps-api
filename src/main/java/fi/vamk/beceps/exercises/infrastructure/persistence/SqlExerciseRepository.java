package fi.vamk.beceps.exercises.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.exercises.infrastructure.persistence.dao.ExerciseWithUser;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class SqlExerciseRepository {
  private final JdbcFetcher jdbcFetcher;

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
}
