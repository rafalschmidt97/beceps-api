package fi.vamk.beceps.exercises.api.events.queries.getexercises;

import fi.vamk.beceps.common.bus.query.Query;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetExercisesQuery implements Query<List<ExerciseWorkoutDto>> {
  private Long userId;
}
