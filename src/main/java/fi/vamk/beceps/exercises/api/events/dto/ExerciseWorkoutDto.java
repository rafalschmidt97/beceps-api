package fi.vamk.beceps.exercises.api.events.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.domain.Workout;
import fi.vamk.beceps.workouts.infrastructure.persistence.dao.WorkoutRoutine;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseWorkoutDto {
  private Long id;
  private String name;

  @JsonInclude
  private ExerciseRoutineDto routine;

  public ExerciseWorkoutDto(Workout workout, Routine routine, List<Exercise> todayExercises) {
    this.id = workout.getId();
    this.name = workout.getName();
    this.routine = new ExerciseRoutineDto(routine, todayExercises);
  }

  public ExerciseWorkoutDto(WorkoutRoutine workoutRoutine, List<Exercise> todayExercises) {
    this.id = workoutRoutine.getWorkoutId();
    this.name = workoutRoutine.getWorkoutName();
    this.routine = new ExerciseRoutineDto(workoutRoutine, todayExercises);
  }
}
