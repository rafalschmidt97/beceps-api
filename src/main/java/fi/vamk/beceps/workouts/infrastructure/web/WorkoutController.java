package fi.vamk.beceps.workouts.infrastructure.web;

import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.workouts.api.WorkoutOperations;
import fi.vamk.beceps.workouts.api.events.commands.addroutine.AddRoutineCommand;
import fi.vamk.beceps.workouts.api.events.commands.addset.AddSetCommand;
import fi.vamk.beceps.workouts.api.events.commands.addworkout.AddWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.commands.removeroutine.RemoveRoutineCommand;
import fi.vamk.beceps.workouts.api.events.commands.removeset.RemoveSetCommand;
import fi.vamk.beceps.workouts.api.events.commands.removeworkout.RemoveWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.commands.updateworkout.UpdateWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import fi.vamk.beceps.workouts.api.events.queries.getworkouts.GetWorkoutsQuery;
import io.micronaut.http.annotation.Controller;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

@Controller()
public class WorkoutController extends SecuredController implements WorkoutOperations {
  @Override
  public List<WorkoutDto> getWorkouts(Principal principal) {
    return bus.executeQuery(new GetWorkoutsQuery(getId(principal)));
  }

  @Override
  public void addWorkout(@Valid AddWorkoutCommand request, Principal principal) {
    request.setUserId(getId(principal));
    bus.executeCommand(request);
  }

  @Override
  public void updateWorkout(Long workoutId, @Valid UpdateWorkoutCommand request, Principal principal) {
    request.setUserId(getId(principal));
    request.setWorkoutId(workoutId);
    bus.executeCommand(request);
  }

  @Override
  public void removeWorkout(Long workoutId, Principal principal) {
    bus.executeCommand(new RemoveWorkoutCommand(workoutId, getId(principal)));
  }

  @Override
  public void addRoutine(@Valid AddRoutineCommand request, Principal principal) {
    request.setUserId(getId(principal));
    bus.executeCommand(request);
  }

  @Override
  public void removeRoutine(Long routineId, Principal principal) {
    bus.executeCommand(new RemoveRoutineCommand(routineId, getId(principal)));
  }

  @Override
  public void addSet(@Valid AddSetCommand request, Principal principal) {
    request.setUserId(getId(principal));
    bus.executeCommand(request);
  }

  @Override
  public void removeSet(Long setId, Principal principal) {
    bus.executeCommand(new RemoveSetCommand(setId, getId(principal)));
  }
}
