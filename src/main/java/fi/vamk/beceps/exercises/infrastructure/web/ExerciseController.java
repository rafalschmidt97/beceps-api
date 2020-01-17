package fi.vamk.beceps.exercises.infrastructure.web;

import fi.vamk.beceps.common.date.DateUtils;
import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.exercises.api.ExerciseOperations;
import fi.vamk.beceps.exercises.api.events.commands.addexercise.AddExerciseCommand;
import fi.vamk.beceps.exercises.api.events.commands.removeexercise.RemoveExerciseCommand;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutDto;
import fi.vamk.beceps.exercises.api.events.dto.ExerciseWorkoutHistoryDto;
import fi.vamk.beceps.exercises.api.events.queries.getexercises.GetExercisesQuery;
import fi.vamk.beceps.exercises.api.events.queries.gethistory.GetExercisesHistoryQuery;
import fi.vamk.beceps.exercises.infrastructure.web.requests.AddExerciseRequest;
import io.micronaut.http.annotation.Controller;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Controller("/exercises")
public class ExerciseController extends SecuredController implements ExerciseOperations {
  @Override
  public List<ExerciseWorkoutDto> getExercises(Principal principal) {
    return bus.executeQuery(new GetExercisesQuery(getId(principal)));
  }

  @Override
  public List<ExerciseWorkoutHistoryDto> getHistory(Optional<Date> from, Principal principal) {
    return bus.executeQuery(
        new GetExercisesHistoryQuery(from.orElse(DateUtils.getWeekAgo()), getId(principal)));
  }

  @Override
  public void addExercise(@Valid AddExerciseRequest request, Principal principal) {
    bus.executeCommand(new AddExerciseCommand(request.getReps(), request.getSetId(), getId(principal)));
  }

  @Override
  public void removeExercise(Long exerciseId, Principal principal) {
    bus.executeCommand(new RemoveExerciseCommand(exerciseId, getId(principal)));
  }
}
