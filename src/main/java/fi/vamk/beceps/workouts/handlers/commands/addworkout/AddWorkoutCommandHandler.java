package fi.vamk.beceps.workouts.handlers.commands.addworkout;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.workouts.api.events.commands.addworkout.AddWorkoutCommand;
import fi.vamk.beceps.workouts.api.events.commands.addworkout.AddWorkoutResponse;
import fi.vamk.beceps.workouts.domain.Workout;
import fi.vamk.beceps.workouts.infrastructure.persistence.WorkoutRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AddWorkoutCommandHandler implements CommandHandler<AddWorkoutResponse, AddWorkoutCommand> {
  private final WorkoutRepository workoutRepository;

  @Override
  public AddWorkoutResponse handle(AddWorkoutCommand command) {
    val workout = new Workout(command.getName(), command.getUserId());
    workoutRepository.insert(workout);

    return new AddWorkoutResponse(workout.getId());
  }
}
