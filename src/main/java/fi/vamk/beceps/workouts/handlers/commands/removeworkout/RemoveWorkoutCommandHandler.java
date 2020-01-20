package fi.vamk.beceps.workouts.handlers.commands.removeworkout;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.removeworkout.RemoveWorkoutCommand;
import fi.vamk.beceps.workouts.domain.Workout;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveWorkoutCommandHandler implements CommandHandler<Void, RemoveWorkoutCommand> {
  private final WorkoutRepository workoutRepository;

  @Override
  public Void handle(RemoveWorkoutCommand command) {
    val workout = workoutRepository
        .findById(command.getWorkoutId())
        .orElseThrow(() -> new NotFoundException(Workout.class, command.getWorkoutId()));

    if (!workout.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    workoutRepository.deleteById(workout.getId());

    return null;
  }
}
