package fi.vamk.beceps.workouts.api.events.commands.addworkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddWorkoutResponse {
  private Long workoutId;
}
