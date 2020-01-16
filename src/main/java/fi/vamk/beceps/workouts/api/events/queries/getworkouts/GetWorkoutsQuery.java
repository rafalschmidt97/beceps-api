package fi.vamk.beceps.workouts.api.events.queries.getworkouts;

import fi.vamk.beceps.common.bus.query.Query;
import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetWorkoutsQuery implements Query<List<WorkoutDto>> {
  private Long userId;
}
