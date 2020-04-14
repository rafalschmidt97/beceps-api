package fi.vamk.beceps.workouts.infrastructure.persistence.dao;

import fi.vamk.beceps.workouts.domain.Set;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutRoutine {
  private Long workoutId;
  private String workoutName;
  private Long userId;
  private Long routineId;
  private String routineName;
  private int weekDay;
  private List<Set> sets;

  public WorkoutRoutine(
      Long workoutId,
      String workoutName,
      Long userId,
      Long routineId,
      String routineName,
      int weekDay
  ) {
    this.workoutId = workoutId;
    this.workoutName = workoutName;
    this.userId = userId;
    this.routineId = routineId;
    this.routineName = routineName;
    this.weekDay = weekDay;
  }
}
