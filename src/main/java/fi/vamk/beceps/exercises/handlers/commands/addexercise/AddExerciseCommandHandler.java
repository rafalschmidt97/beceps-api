package fi.vamk.beceps.exercises.handlers.commands.addexercise;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.exercises.api.events.commands.addexercise.AddExerciseCommand;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.SetRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AddExerciseCommandHandler implements CommandHandler<Void, AddExerciseCommand> {
  private final SetRepository setRepository;
  private final ExerciseRepository exerciseRepository;

  @Override
  @Transactional
  public Void handle(AddExerciseCommand command) {
    val set = setRepository
        .findById(command.getSetId())
        .orElseThrow(() -> new NotFoundException(Set.class, command.getSetId()));

    if (!set.getRoutine().getWorkout().getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    val exercise = new Exercise(command.getReps(), command.getSetId(), command.getUserId());
    exerciseRepository.insert(exercise);

    return null;
  }
}
