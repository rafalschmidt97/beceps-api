package fi.vamk.beceps.workouts.infrastructure.persistence;

import fi.vamk.beceps.common.jdbc.JdbcFetcher;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.domain.Workout;
import fi.vamk.beceps.workouts.infrastructure.persistence.dao.WorkoutRoutine;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@JdbcRepository(dialect = Dialect.MYSQL)
@RequiredArgsConstructor
public abstract class WorkoutRepository implements GenericRepository<Workout, Long> {
  private final JdbcFetcher jdbcFetcher;

  @Transactional
  public List<Workout> findAllWithRoutinesAndSetsByUserId(Long userId) {
    val workoutsQuery = "select * from workout where user_id = ?";
    val routinesQuery = "select * from routine where workout_id = ?";
    val setsQuery = "select * from `set` where routine_id = ?";

    // TODO: optimise nested chain of queries by aggregating ids0
    return jdbcFetcher.fetch(workoutsQuery, statement -> {
      statement.setLong(1, userId);
    }, record -> new Workout(
        record.getLong("id"),
        record.getString("name"),
        null,
        null,
        record.getLong("user_id"),
        null,
        null
    )).stream().peek(workout -> {
      workout.setRoutines(
          jdbcFetcher.fetch(routinesQuery, statement -> {
            statement.setLong(1, workout.getId());
          }, record -> new Routine(
            record.getLong("id"),
            record.getString("name"),
            record.getInt("week_day"),
            null,
            null,
            record.getLong("workout_id"),
            null,
            null
          ))
      );
    }).peek(workout -> {
      workout.getRoutines().forEach(routine -> {
        routine.setSets(
            jdbcFetcher.fetch(setsQuery, statement -> {
              statement.setLong(1, routine.getId());
            }, record -> new Set(
              record.getLong("id"),
              record.getString("name"),
              record.getInt("sets_amount"),
              record.getInt("reps_amount"),
              null,
              null,
              record.getLong("routine_id"),
              null
            ))
        );
      });
    }).collect(Collectors.toList());
  }

  @Transactional
  public List<WorkoutRoutine> findAllWithRoutinesAndSetsByUserIdAndWeekDay(Long userId, int weekDay) {
    val workoutsQuery = "select workout.id as workout_id, workout.name as workout_name, user_id, "
                      + "routine.id as routine_id, routine.name as routine_name, week_day from workout "
                      + "inner join routine on workout.id = routine.workout_id "
                      + "where user_id = ? and week_day = ?;";
    val setsQuery = "select * from `set` where routine_id = ?";

    // TODO: optimise nested chain of queries by aggregating ids0
    return jdbcFetcher.fetch(workoutsQuery, statement -> {
      statement.setLong(1, userId);
      statement.setLong(2, weekDay);
    }, record -> new WorkoutRoutine(
      record.getLong("workout_id"),
      record.getString("workout_name"),
      record.getLong("user_id"),
      record.getLong("routine_id"),
      record.getString("routine_name"),
      record.getInt("week_day")
    )).stream().peek(workoutRoutine -> {
      workoutRoutine.setSets(
          jdbcFetcher.fetch(setsQuery, statement ->
            statement.setLong(1, workoutRoutine.getRoutineId()), record -> new Set(
            record.getLong("id"),
            record.getString("name"),
            record.getInt("sets_amount"),
            record.getInt("reps_amount"),
            null,
            null,
            record.getLong("routine_id"),
            null
          ))
      );
    }).collect(Collectors.toList());
  }

  public abstract Optional<Workout> findById(Long id);

  public abstract Workout insert(Workout workout);

  @Transactional
  public void update(Workout workout) {
    val query = "update workout set name = ?, modified_at = ? where id = ?";

    jdbcFetcher.execute(query, statement -> {
      statement.setString(1, workout.getName());
      statement.setObject(2, workout.getModifiedAt());
      statement.setLong(3, workout.getId());
    });
  }

  public abstract void deleteById(Long id);
}
