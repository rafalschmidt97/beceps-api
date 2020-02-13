package fi.vamk.beceps.workouts.api.events.commands.addworkout;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddWorkoutCommand implements Command<AddWorkoutResponse> {
  private String name;
  private Long userId;
}
