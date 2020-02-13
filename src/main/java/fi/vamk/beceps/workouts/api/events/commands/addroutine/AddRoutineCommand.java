package fi.vamk.beceps.workouts.api.events.commands.addroutine;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRoutineCommand implements Command<AddRoutineResponse> {
  private int weekDay;
  private Long workoutId;
  private Long userId;
}
