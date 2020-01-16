package fi.vamk.beceps.workouts.api.events.commands.updateworkout;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Introspected
public class UpdateWorkoutCommand implements Command<Void> {
  @NotBlank
  @Size(max = 30)
  private String name;

  private Long workoutId;
  private Long userId;
}
