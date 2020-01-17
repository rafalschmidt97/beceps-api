package fi.vamk.beceps.exercises.infrastructure.web;

import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.exercises.api.ExercisesOperations;
import fi.vamk.beceps.exercises.api.events.commands.addexercise.AddExerciseCommand;
import fi.vamk.beceps.exercises.api.events.commands.removeexercise.RemoveExerciseCommand;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutDto;
import fi.vamk.beceps.exercises.api.events.queries.getexercises.GetExercisesQuery;
import io.micronaut.http.annotation.Controller;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

@Controller("/exercises")
public class ExerciseController extends SecuredController implements ExercisesOperations {
  @Override
  public List<ExerciseWorkoutDto> getExercises(Principal principal) {
    return bus.executeQuery(new GetExercisesQuery(getId(principal)));
  }

  @Override
  public void addExercise(@Valid AddExerciseCommand request, Principal principal) {
    request.setUserId(getId(principal));
    bus.executeCommand(request);
  }

  @Override
  public void removeExercise(Long exerciseId, Principal principal) {
    bus.executeCommand(new RemoveExerciseCommand(exerciseId, getId(principal)));
  }
}
