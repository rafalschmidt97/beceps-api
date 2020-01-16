package fi.vamk.beceps.workouts.api.events.commands.removeset;

import fi.vamk.beceps.common.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveSetCommand implements Command<Void> {
  private Long setId;
  private Long userId;
}
