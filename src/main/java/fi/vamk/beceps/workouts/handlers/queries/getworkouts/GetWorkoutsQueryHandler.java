package fi.vamk.beceps.workouts.handlers.queries.getworkouts;

import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import fi.vamk.beceps.workouts.api.events.queries.getworkouts.GetWorkoutsQuery;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class GetWorkoutsQueryHandler implements QueryHandler<List<WorkoutDto>, GetWorkoutsQuery> {
  private final WorkoutRepository workoutRepository;

  @Override
  @Transactional
  public List<WorkoutDto> handle(GetWorkoutsQuery query) {
    return workoutRepository
      .findAllByUserId(query.getUserId())
      .stream()
      .map(WorkoutDto::new)
      .collect(Collectors.toList());
  }
}
