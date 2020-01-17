package fi.vamk.beceps.workouts.handlers.commands.addroutine;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.addroutine.AddRoutineCommand;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.domain.Workout;
import fi.vamk.beceps.workouts.infrastructure.persistence.RoutineRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AddRoutineCommandHandler implements CommandHandler<Void, AddRoutineCommand> {
  private final RoutineRepository routineRepository;
  private final WorkoutRepository workoutRepository;

  @Override
  public Void handle(AddRoutineCommand command) {
    val workout = workoutRepository
        .findById(command.getWorkoutId())
        .orElseThrow(() -> new NotFoundException(Workout.class, command.getWorkoutId()));

    if (!workout.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    val routine = new Routine(command.getWeekDay(), command.getWorkoutId());
    routineRepository.insert(routine);

    return null;
  }
}
