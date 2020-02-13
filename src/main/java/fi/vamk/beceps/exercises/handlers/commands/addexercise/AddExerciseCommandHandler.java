package fi.vamk.beceps.exercises.handlers.commands.addexercise;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.date.TimeUtils;
import fi.vamk.beceps.common.exceptions.ConflictException;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.exercises.api.events.commands.addexercise.AddExerciseCommand;
import fi.vamk.beceps.exercises.api.events.commands.addexercise.AddExerciseResponse;
import fi.vamk.beceps.exercises.domain.Exercise;
import fi.vamk.beceps.exercises.infrastructure.persistence.ExerciseRepository;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.SetRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AddExerciseCommandHandler implements CommandHandler<AddExerciseResponse, AddExerciseCommand> {
  private final SetRepository sqlSetRepository;
  private final ExerciseRepository exerciseRepository;

  @Override
  public AddExerciseResponse handle(AddExerciseCommand command) {
    val setCheck = sqlSetRepository
        .findWithUserIdById(command.getSetId())
        .orElseThrow(() -> new NotFoundException(Set.class, command.getSetId()));

    if (!setCheck.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    if (setCheck.getWeekDay() != TimeUtils.getWeekDay()) {
      throw new ConflictException(
        String.format(
          "%s(%s) is not in today.",
          Set.class.getSimpleName(),
          setCheck.getId()
        )
      );
    }

    val exercise = new Exercise(command.getReps(), command.getSetId(), command.getUserId());
    exerciseRepository.insert(exercise);

    return new AddExerciseResponse(exercise.getId());
  }
}
