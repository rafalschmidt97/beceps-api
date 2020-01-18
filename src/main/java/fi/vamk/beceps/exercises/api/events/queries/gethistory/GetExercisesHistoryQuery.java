package fi.vamk.beceps.exercises.api.events.queries.gethistory;

import fi.vamk.beceps.common.bus.query.Query;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutHistoryDto;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetExercisesHistoryQuery implements Query<List<ExerciseWorkoutHistoryDto>> {
  private Instant from;
  private Long userId;
}
