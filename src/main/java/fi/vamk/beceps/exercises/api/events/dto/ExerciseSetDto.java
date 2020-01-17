package fi.vamk.beceps.exercises.api.events.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.workouts.domain.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseSetDto {
  private Long id;
  private String name;
  private int setsAmount;
  private int repsAmount;

  @JsonInclude
  private List<ExerciseDto> exercises;

  public ExerciseSetDto(Set set, List<Exercise> exercises) {
    this.id = set.getId();
    this.name = set.getName();
    this.setsAmount = set.getSetsAmount();
    this.repsAmount = set.getRepsAmount();
    this.exercises = exercises
      .stream()
      .map(ExerciseDto::new)
      .collect(Collectors.toList());
  }

  public ExerciseSetDto(Set set) {
    this(set, new ArrayList<>());
  }
}
