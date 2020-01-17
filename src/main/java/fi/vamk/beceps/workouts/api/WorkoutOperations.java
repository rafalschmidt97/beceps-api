package fi.vamk.beceps.workouts.api;

import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import fi.vamk.beceps.workouts.infrastructure.web.requests.AddRoutineRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.AddSetRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.AddWorkoutRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.UpdateRoutineRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.UpdateSetRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.UpdateWorkoutRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

@Tag(name = "Workouts")
public interface WorkoutOperations {
  @Get("/workouts")
  @Operation(summary = "Get own workouts.")
  List<WorkoutDto> getWorkouts(Principal principal);

  @Post("/workouts")
  @Operation(summary = "Add new workout.")
  void addWorkout(@Valid @Body AddWorkoutRequest request, Principal principal);

  @Put("/workouts/{workoutId}")
  @Operation(summary = "Update existing workout.")
  void updateWorkout(Long workoutId, @Valid @Body UpdateWorkoutRequest request, Principal principal);

  @Delete("/workouts/{workoutId}")
  @Operation(summary = "Remove existing workout.")
  void removeWorkout(Long workoutId, Principal principal);

  @Post("/routines")
  @Operation(summary = "Add new routine.")
  void addRoutine(@Valid @Body AddRoutineRequest request, Principal principal);

  @Put("/routines/{routineId}")
  @Operation(summary = "Update existing routine.")
  void updateRoutine(Long routineId, @Valid @Body UpdateRoutineRequest request, Principal principal);

  @Delete("/routines/{routineId}")
  @Operation(summary = "Remove existing routine.")
  void removeRoutine(Long routineId, Principal principal);

  @Post("/sets")
  @Operation(summary = "Add new set.")
  void addSet(@Valid @Body AddSetRequest request, Principal principal);

  @Put("/sets/{setId}")
  @Operation(summary = "Update existing routine.")
  void updateSet(Long setId, @Valid @Body UpdateSetRequest request, Principal principal);

  @Delete("/sets/{setId}")
  @Operation(summary = "Remove existing set.")
  void removeSet(Long setId, Principal principal);
}
