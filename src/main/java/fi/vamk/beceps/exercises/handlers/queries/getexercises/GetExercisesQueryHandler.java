package fi.vamk.beceps.exercises.handlers.queries.getexercises;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.common.date.TimeUtils;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutDto;
import fi.vamk.beceps.exercises.api.events.queries.getexercises.GetExercisesQuery;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.RoutineRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class GetExercisesQueryHandler implements QueryHandler<List<ExerciseWorkoutDto>, GetExercisesQuery> {
  private final WorkoutRepository workoutRepository;
  private final RoutineRepository routineRepository;
  private final ExerciseRepository exerciseRepository;

  @Override
  @Transactional
  public List<ExerciseWorkoutDto> handle(GetExercisesQuery query) {
    val todayExercises = exerciseRepository
        .findAllByUserIdAndCreatedAtAfter(query.getUserId(), TimeUtils.getTodayMidnight());

    return workoutRepository
      .findAllByUserId(query.getUserId())
      .stream()
      .map(workout -> {
        val routine = routineRepository
            .findByWorkoutIdAndWeekDay(workout.getId(), TimeUtils.getWeekDay())
            .orElseThrow(() -> new NotFoundException("No routines available today."));

        return new ExerciseWorkoutDto(workout, routine, todayExercises);
      })
      .collect(Collectors.toList());
  }
}
