package fi.vamk.beceps.exercises.api.events.commands.addexercise;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddExerciseResponse {
  private Long exerciseId;
}
