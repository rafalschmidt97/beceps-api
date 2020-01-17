package fi.vamk.beceps.exercises.api.events.dto;

import fi.vamk.beceps.exercises.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseDto {
  private Long id;
  private int reps;

  public ExerciseDto(Exercise exercise) {
    this.id = exercise.getId();
    this.reps = exercise.getReps();
  }
}
