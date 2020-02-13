package fi.vamk.beceps.workouts.infrastructure.web;

import fi.vamk.beceps.core.auth.infrastructure.web.SecuredController;
import fi.vamk.beceps.workouts.api.WorkoutOperations;
import fi.vamk.beceps.workouts.api.events.commands.addroutine.AddRoutineCommand;
import fi.vamk.beceps.workouts.api.events.commands.addroutine.AddRoutineResponse;
import fi.vamk.beceps.workouts.api.events.commands.addset.AddSetCommand;
import fi.vamk.beceps.workouts.api.events.commands.addset.AddSetResponse;
import fi.vamk.beceps.workouts.api.events.commands.addworkout.AddWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.commands.addworkout.AddWorkoutResponse;
import fi.vamk.beceps.workouts.api.events.commands.removeroutine.RemoveRoutineCommand;
import fi.vamk.beceps.workouts.api.events.commands.removeset.RemoveSetCommand;
import fi.vamk.beceps.workouts.api.events.commands.removeworkout.RemoveWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.commands.updateset.UpdateSetCommand;
import fi.vamk.beceps.workouts.api.events.commands.updateworkout.UpdateWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.dto.WorkoutDto;
import fi.vamk.beceps.workouts.api.events.queries.getworkouts.GetWorkoutsQuery;
import fi.vamk.beceps.workouts.infrastructure.web.requests.AddRoutineRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.AddSetRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.AddWorkoutRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.UpdateRoutineRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.UpdateSetRequest;
import fi.vamk.beceps.workouts.infrastructure.web.requests.UpdateWorkoutRequest;
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
  public AddWorkoutResponse addWorkout(@Valid AddWorkoutRequest request, Principal principal) {
    return bus.executeCommand(new AddWorkoutCommand(request.getName(), getId(principal)));
  }

  @Override
  public void updateWorkout(Long workoutId, @Valid UpdateWorkoutRequest request, Principal principal) {
    bus.executeCommand(new UpdateWorkoutCommand(request.getName(), workoutId, getId(principal)));
  }

  @Override
  public void removeWorkout(Long workoutId, Principal principal) {
    bus.executeCommand(new RemoveWorkoutCommand(workoutId, getId(principal)));
  }

  @Override
  public AddRoutineResponse addRoutine(@Valid AddRoutineRequest request, Principal principal) {
    return bus.executeCommand(new AddRoutineCommand(request.getWeekDay(), request.getWorkoutId(), getId(principal)));
  }

  @Override
  public void updateRoutine(Long routineId, UpdateRoutineRequest request, Principal principal) {
    bus.executeCommand(new AddRoutineCommand(request.getWeekDay(), routineId, getId(principal)));
  }

  @Override
  public void removeRoutine(Long routineId, Principal principal) {
    bus.executeCommand(new RemoveRoutineCommand(routineId, getId(principal)));
  }

  @Override
  public AddSetResponse addSet(@Valid AddSetRequest request, Principal principal) {
    return bus.executeCommand(
        new AddSetCommand(
            request.getName(),
            request.getSetsAmount(),
            request.getRepsAmount(),
            request.getRoutineId(),
            getId(principal))
    );
  }

  @Override
  public void updateSet(Long setId, UpdateSetRequest request, Principal principal) {
    bus.executeCommand(
        new UpdateSetCommand(
            request.getName(),
            request.getSetsAmount(),
            request.getRepsAmount(),
            setId,
            getId(principal))
    );
  }

  @Override
  public void removeSet(Long setId, Principal principal) {
    bus.executeCommand(new RemoveSetCommand(setId, getId(principal)));
  }
}
