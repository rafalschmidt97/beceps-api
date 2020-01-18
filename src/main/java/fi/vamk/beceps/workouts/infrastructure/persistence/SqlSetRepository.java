package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.workouts.infrastructure.persistence.dao.SetWithUser;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class SqlSetRepository {
  private final JdbcFetcher jdbcFetcher;

  public Optional<SetWithUser> findWithUserIdById(Long id) {
    val query = "select set.id as id, routine.week_day as week_day, workout.user_id as user_id from `set` " +
                "inner join routine on `set`.routine_id = routine.id " +
                "inner join workout on routine.workout_id = workout.id " +
                "where `set`.id = ? " +
                "limit 1";

    return jdbcFetcher.fetch(query, statement -> {
      statement.setLong(1, id);
    }, record -> new SetWithUser(
        record.getLong("id"),
        record.getLong("user_id"),
        record.getInt("week_day")
    )).stream().findFirst();
  }
}
