package fi.vamk.beceps.exercises.api.events.commands.addexercise;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddExerciseCommand implements Command<AddExerciseResponse> {
  private int reps;
  private Long setId;
  private Long userId;
}
