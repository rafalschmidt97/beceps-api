package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.dao.SetWithUser;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@JdbcRepository(dialect = Dialect.MYSQL)
@RequiredArgsConstructor
public abstract class SetRepository implements GenericRepository<Set, Long> {
  private final JdbcFetcher jdbcFetcher;

  public abstract Optional<Set> findById(Long id);

  @Transactional
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

  public abstract Set insert(Set set);

  @Transactional
  public void update(Set set) {
    val query = "update `set` set name = ?, sets_amount = ?, reps_amount = ?, modified_at = ? where id = ?";

    jdbcFetcher.execute(query, statement -> {
      statement.setString(1, set.getName());
      statement.setInt(2, set.getSetsAmount());
      statement.setInt(3, set.getRepsAmount());
      statement.setObject(4, set.getModifiedAt());
      statement.setLong(5, set.getId());
    });
  }

  public abstract void deleteById(Long id);
}
