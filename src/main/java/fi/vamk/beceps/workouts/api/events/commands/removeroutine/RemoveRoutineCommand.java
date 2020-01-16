package fi.vamk.beceps.workouts.api.events.commands.removeroutine;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveRoutineCommand implements Command<Void> {
  private Long routineId;
  private Long userId;
}
