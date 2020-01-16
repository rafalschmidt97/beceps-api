package fi.vamk.beceps.workouts.commands.removeroutine;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.removeroutine.RemoveRoutineCommand;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.infrastructure.persistence.RoutineRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveRoutineCommandHandler implements CommandHandler<Void, RemoveRoutineCommand> {
  private final RoutineRepository routineRepository;

  @Override
  @Transactional
  public Void handle(RemoveRoutineCommand command) {
    val routine = routineRepository
        .findById(command.getRoutineId())
        .orElseThrow(() -> new NotFoundException(Routine.class, command.getRoutineId()));

    if (!routine.getWorkout().getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    routineRepository.delete(routine);

    return null;
  }
}
