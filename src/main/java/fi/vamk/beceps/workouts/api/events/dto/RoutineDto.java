package fi.vamk.beceps.workouts.api.events.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vamk.beceps.workouts.domain.Routine;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoutineDto {
  private Long id;
  private String name;
  private int weekDay;

  @JsonInclude
  private List<SetDto> sets;

  public RoutineDto(Routine routine) {
    this.id = routine.getId();
    this.name = routine.getName();
    this.weekDay = routine.getWeekDay();
    this.sets = routine.getSets()
      .stream()
      .map(SetDto::new)
      .collect(Collectors.toList());
  }
}
