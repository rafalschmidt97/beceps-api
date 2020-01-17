package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.workouts.infrastructure.persistence.dao.RoutineWithUser;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class SqlRoutineRepository {
  private final JdbcFetcher jdbcFetcher;

  public Optional<RoutineWithUser> findWithUserIdById(Long id) {
    val query = "select routine.id as id, workout.user_id as user_id from routine " +
                "inner join workout on routine.workout_id = workout.id " +
                "where routine.id = ?";

    return jdbcFetcher.fetch(query, statement -> {
      statement.setLong(1, id);
    }, record -> new RoutineWithUser(
        record.getLong("id"),
        record.getLong("user_id")
    )).stream().findFirst();
  }
}
