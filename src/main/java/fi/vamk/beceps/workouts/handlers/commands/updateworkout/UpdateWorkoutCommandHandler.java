package fi.vamk.beceps.workouts.handlers.commands.updateworkout;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.updateworkout.UpdateWorkoutCommand;
import fi.vamk.beceps.workouts.domain.Workout;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class UpdateWorkoutCommandHandler implements CommandHandler<Void, UpdateWorkoutCommand> {
  private final WorkoutRepository workoutRepository;

  @Override
  public Void handle(UpdateWorkoutCommand command) {
    val workout = workoutRepository
        .findById(command.getWorkoutId())
        .orElseThrow(() -> new NotFoundException(Workout.class, command.getWorkoutId()));

    if (!workout.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    workout.update(command.getName());
    workoutRepository.update(workout);

    return null;
  }
}
