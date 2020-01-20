package fi.vamk.beceps.workouts.handlers.queries.getworkouts;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import fi.vamk.beceps.workouts.api.events.queries.getworkouts.GetWorkoutsQuery;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class GetWorkoutsQueryHandler implements QueryHandler<List<WorkoutDto>, GetWorkoutsQuery> {
  private final WorkoutRepository workoutRepository;

  @Override
  public List<WorkoutDto> handle(GetWorkoutsQuery query) {
    return workoutRepository
      .findAllWithRoutinesAndSetsByUserId(query.getUserId())
      .stream()
      .map(WorkoutDto::new)
      .collect(Collectors.toList());
  }
}
