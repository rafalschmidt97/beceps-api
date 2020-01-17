package fi.vamk.beceps.exercises.infrastructure.web.requests;

import fi.vamk.beceps.common.bus.command.Command;
import io.micronaut.core.annotation.Introspected;
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
public class AddExerciseRequest implements Command<Void> {
  @NotBlank
  @Min(1)
  private int reps;

  @NotBlank
  private Long setId;
}
