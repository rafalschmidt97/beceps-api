package fi.vamk.beceps.workouts.api.events.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoutineDto {
  private List<SetDto> routines;
}
