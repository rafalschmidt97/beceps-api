package fi.vamk.beceps.exercises.api;

import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutDto;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutHistoryDto;
import fi.vamk.beceps.exercises.infrastructure.web.requests.AddExerciseRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Tag(name = "Exercises")
public interface ExerciseOperations {
  @Get("/today")
  @Operation(summary = "Get today exercises progress.")
  List<ExerciseWorkoutDto> getExercises(Principal principal);

  @Get("/history{?from}")
  @Operation(summary = "Get history exercises.")
  List<ExerciseWorkoutHistoryDto> getHistory(Optional<Instant> from, Principal principal);

  @Post()
  @Operation(summary = "Add new exercise progress.")
  void addExercise(@Valid @Body AddExerciseRequest request, Principal principal);

  @Delete("/{exerciseId}")
  @Operation(summary = "Remove existing exercise progress.")
  void removeExercise(Long exerciseId, Principal principal);
}
