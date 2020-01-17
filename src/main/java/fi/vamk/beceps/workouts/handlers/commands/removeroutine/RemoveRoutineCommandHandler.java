package fi.vamk.beceps.workouts.handlers.commands.removeroutine;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.removeroutine.RemoveRoutineCommand;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.infrastructure.persistence.RoutineRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.SqlRoutineRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveRoutineCommandHandler implements CommandHandler<Void, RemoveRoutineCommand> {
  private final RoutineRepository routineRepository;
  private final SqlRoutineRepository sqlRoutineRepository;

  @Override
  @Transactional
  public Void handle(RemoveRoutineCommand command) {
    val routine = sqlRoutineRepository
        .findWithUserIdById(command.getRoutineId())
        .orElseThrow(() -> new NotFoundException(Routine.class, command.getRoutineId()));

    if (!routine.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    routineRepository.deleteById(routine.getId());

    return null;
  }
}
