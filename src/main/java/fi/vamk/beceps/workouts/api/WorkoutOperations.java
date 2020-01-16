package fi.vamk.beceps.workouts.api;

import fi.vamk.beceps.workouts.api.events.commands.addroutine.AddRoutineCommand;
import fi.vamk.beceps.workouts.api.events.commands.addset.AddSetCommand;
import fi.vamk.beceps.workouts.api.events.commands.addworkout.AddWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.commands.updateroutine.UpdateRoutineCommand;
import fi.vamk.beceps.workouts.api.events.commands.updateworkout.UpdateWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;

@Tag(name = "Workouts")
public interface WorkoutOperations {
  @Get("/workouts")
  @Operation(summary = "Get own workouts.")
  List<WorkoutDto> getWorkouts();

  @Post("/workouts")
  @Operation(summary = "Add new workout.")
  void addWorkout(@Valid @Body AddWorkoutCommand request);

  @Put("/workouts/{workoutId}")
  @Operation(summary = "Update existing workout.")
  void updateWorkout(Long workoutId, @Valid @Body UpdateWorkoutCommand request);

  @Delete("/workouts/{workoutId}")
  @Operation(summary = "Remove existing workout.")
  void removeWorkout(Long workoutId);

  @Post("/routines")
  @Operation(summary = "Add new routine.")
  void addRoutine(@Valid @Body AddRoutineCommand request);

  @Put("/routines/{routineId}")
  @Operation(summary = "Update existing routine.")
  void updateRoutine(Long routineId, @Valid @Body UpdateRoutineCommand request);

  @Delete("/routines/{routineId}")
  @Operation(summary = "Remove existing routine.")
  void removeRoutine(Long routineId);

  @Post("/sets")
  @Operation(summary = "Add new set.")
  void addSet(@Valid @Body AddSetCommand request);

  @Delete("/sets/{setId}")
  @Operation(summary = "Remove existing set.")
  void removeSet(Long setId);
}
