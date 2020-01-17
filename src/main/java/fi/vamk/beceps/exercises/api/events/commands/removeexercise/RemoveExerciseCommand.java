package fi.vamk.beceps.exercises.api.events.commands.removeexercise;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveExerciseCommand implements Command<Void> {
  private Long exerciseId;
  private Long userId;
}
