package fi.vamk.beceps.workouts.api.events.commands.addroutine;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Introspected
public class AddRoutineCommand implements Command<Void> {
  @NotBlank
  private int weekDay;

  @NotBlank
  private Long workoutId;

  private Long userId;
}
