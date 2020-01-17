package fi.vamk.beceps.workouts.api.events.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vamk.beceps.workouts.domain.Workout;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutDto {
  private Long id;
  private String name;

  @JsonInclude
  private List<RoutineDto> routines;

  public WorkoutDto(Workout workout) {
    this.id = workout.getId();
    this.name = workout.getName();
    this.routines = workout.getRoutines()
      .stream()
      .map(RoutineDto::new)
      .collect(Collectors.toList());
  }
}
