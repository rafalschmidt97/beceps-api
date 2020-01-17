package fi.vamk.beceps.workouts.api.events.commands.updateroutine;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRoutineCommand implements Command<Void> {
  private int weekDay;
  private Long routineId;
  private Long userId;
}
