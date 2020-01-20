package fi.vamk.beceps.exercises.handlers.queries.gethistory;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.common.date.TimeUtils;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutHistoryDto;
import fi.vamk.beceps.exercises.api.events.queries.gethistory.GetExercisesHistoryQuery;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class GetExercisesHistoryQueryHandler implements
    QueryHandler<List<ExerciseWorkoutHistoryDto>, GetExercisesHistoryQuery> {

  private final WorkoutRepository workoutRepository;
  private final ExerciseRepository exerciseRepository;

  @Override
  public List<ExerciseWorkoutHistoryDto> handle(GetExercisesHistoryQuery query) {
    val exercises = exerciseRepository.findAllByUserIdAndCreatedAtBetween(
        query.getUserId(),
        query.getFrom(),
        TimeUtils.getWeekLater(query.getFrom())
    );

    return workoutRepository
      .findAllWithRoutinesAndSetsByUserId(query.getUserId())
      .stream()
      .map(workout -> new ExerciseWorkoutHistoryDto(workout, exercises))
      .collect(Collectors.toList());
  }
}
