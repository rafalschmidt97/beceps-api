package fi.vamk.beceps.exercises.handlers.commands.removeexercise;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.exercises.api.events.commands.removeexercise.RemoveExerciseCommand;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveExerciseCommandHandler implements CommandHandler<Void, RemoveExerciseCommand> {
  private final ExerciseRepository exerciseRepository;

  @Override
  public Void handle(RemoveExerciseCommand command) {
    val exercise = exerciseRepository
        .findWithUserIdById(command.getExerciseId())
        .orElseThrow(() -> new NotFoundException(Exercise.class, command.getExerciseId()));

    if (!exercise.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    exerciseRepository.deleteById(exercise.getId());

    return null;
  }
}
