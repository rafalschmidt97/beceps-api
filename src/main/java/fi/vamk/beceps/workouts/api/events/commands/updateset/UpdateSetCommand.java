package fi.vamk.beceps.workouts.api.events.commands.updateset;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSetCommand implements Command<Void> {
  private String name;
  private int setsAmount;
  private int repsAmount;
  private Long setId;
  private Long userId;
}
