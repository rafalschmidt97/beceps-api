package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.infrastructure.persistence.dao.RoutineWithUser;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@JdbcRepository(dialect = Dialect.MYSQL)
@RequiredArgsConstructor
public abstract class RoutineRepository implements GenericRepository<Routine, Long> {
  private final JdbcFetcher jdbcFetcher;

  public abstract Optional<Routine> findById(Long id);

  @Transactional
  public Optional<RoutineWithUser> findWithUserIdById(Long id) {
    val query = "select routine.id as id, workout.user_id as user_id from routine " +
                "inner join workout on routine.workout_id = workout.id " +
                "where routine.id = ? " +
                "limit 1";

    return jdbcFetcher.fetch(query, statement -> {
      statement.setLong(1, id);
    }, record -> new RoutineWithUser(
      record.getLong("id"),
      record.getLong("user_id")
    )).stream().findFirst();
  }

  public abstract Routine insert(Routine routine);

  @Transactional
  public void update(Routine routine) {
    val query = "update routine set week_day = ?, modified_at = ? where id = ?";

    jdbcFetcher.execute(query, statement -> {
      statement.setInt(1, routine.getWeekDay());
      statement.setObject(2, routine.getModifiedAt());
      statement.setLong(3, routine.getId());
    });
  }

  public abstract void deleteById(Long id);
}
