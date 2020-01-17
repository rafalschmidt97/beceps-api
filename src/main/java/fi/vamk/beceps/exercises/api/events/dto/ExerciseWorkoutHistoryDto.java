package fi.vamk.beceps.exercises.api.events.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.workouts.domain.Workout;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseWorkoutHistoryDto {
  private Long id;
  private String name;

  @JsonInclude
  private List<ExerciseRoutineDto> routines;

  public ExerciseWorkoutHistoryDto(Workout workout, List<Exercise> exercises) {
    this.id = workout.getId();
    this.name = workout.getName();
    this.routines = workout.getRoutines()
      .stream()
      .map(routine -> new ExerciseRoutineDto(routine, exercises))
      .collect(Collectors.toList());
  }
}
