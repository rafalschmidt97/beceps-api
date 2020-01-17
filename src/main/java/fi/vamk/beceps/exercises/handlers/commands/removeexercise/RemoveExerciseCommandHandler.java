package fi.vamk.beceps.exercises.handlers.commands.removeexercise;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.exercises.api.events.commands.removeexercise.RemoveExerciseCommand;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveExerciseCommandHandler implements CommandHandler<Void, RemoveExerciseCommand> {
  private final ExerciseRepository exerciseRepository;

  @Override
  @Transactional
  public Void handle(RemoveExerciseCommand command) {
    val exercise = exerciseRepository
        .findById(command.getExerciseId())
        .orElseThrow(() -> new NotFoundException(Exercise.class, command.getExerciseId()));

    if (!exercise.getSet().getRoutine().getWorkout().getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    exerciseRepository.delete(exercise);

    return null;
  }
}
