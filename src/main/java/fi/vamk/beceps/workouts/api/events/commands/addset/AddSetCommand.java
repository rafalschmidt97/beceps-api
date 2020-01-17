package fi.vamk.beceps.workouts.api.events.commands.addset;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddSetCommand implements Command<Void> {
  private String name;
  private int setsAmount;
  private int repsAmount;
  private Long routineId;
  private Long userId;
}
