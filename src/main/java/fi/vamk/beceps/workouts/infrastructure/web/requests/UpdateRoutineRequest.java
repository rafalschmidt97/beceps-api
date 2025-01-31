package fi.vamk.beceps.workouts.infrastructure.web.requests;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Max;
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
public class UpdateRoutineRequest {
  @NotBlank
  @Size(max = 30)
  private String name;

  @NotBlank
  @Min(1)
  @Max(7)
  private int weekDay;
}
