package fi.vamk.beceps.workouts.api.events.commands.updateset;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Min;
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
public class UpdateSetCommand implements Command<Void> {
  @NotBlank
  @Size(max = 30)
  private String name;

  @NotBlank
  @Min(1)
  private int setsAmount;

  @NotBlank
  @Min(1)
  private int repsAmount;

  private Long setId;
  private Long userId;
}
