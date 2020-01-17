package fi.vamk.beceps.exercises.api.events.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.workouts.domain.Routine;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;

@Data
@AllArgsConstructor
public class ExerciseRoutineDto {
  private Long id;
  private int weekDay;

  @JsonInclude
  private List<ExerciseSetDto> sets;

  public ExerciseRoutineDto(Routine routine, List<Exercise> todayExercises) {
    this.id = routine.getId();
    this.weekDay = routine.getWeekDay();
    this.sets = routine.getSets()
      .stream()
      .map(set -> {
        val setExercises = todayExercises
            .stream()
            .filter(exercise -> exercise.getSetId().equals(set.getId()))
            .collect(Collectors.toList());

        return new ExerciseSetDto(set, setExercises);
      })
      .collect(Collectors.toList());
  }
}
