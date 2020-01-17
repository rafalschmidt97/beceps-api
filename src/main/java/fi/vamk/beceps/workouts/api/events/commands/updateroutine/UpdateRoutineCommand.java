package fi.vamk.beceps.workouts.api.events.commands.updateroutine;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Introspected
public class UpdateRoutineCommand implements Command<Void> {
  @NotBlank
  @Min(1)
  @Max(7)
  private int weekDay;

  private Long routineId;
  private Long userId;
}
