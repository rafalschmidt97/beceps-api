package fi.vamk.beceps.exercises.handlers.queries.getexercises;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.common.date.TimeUtils;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutDto;
import fi.vamk.beceps.exercises.api.events.queries.getexercises.GetExercisesQuery;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class GetExercisesQueryHandler implements QueryHandler<List<ExerciseWorkoutDto>, GetExercisesQuery> {
  private final WorkoutRepository workoutRepository;
  private final ExerciseRepository exerciseRepository;

  @Override
  public List<ExerciseWorkoutDto> handle(GetExercisesQuery query) {
    val todayExercises = exerciseRepository
        .findAllByUserIdAndCreatedAtAfter(query.getUserId(), TimeUtils.getTodayMidnight());

    return workoutRepository
      .findAllWithRoutinesAndSetsByUserIdAndWeekDay(query.getUserId(), TimeUtils.getWeekDay())
      .stream()
      .map(workoutRoutine -> new ExerciseWorkoutDto(workoutRoutine, todayExercises))
      .collect(Collectors.toList());
  }
}
