package fi.vamk.beceps.workouts.handlers.commands.updateroutine;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.updateroutine.UpdateRoutineCommand;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.infrastructure.persistence.RoutineRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.SqlRoutineRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class UpdateRoutineCommandHandler implements CommandHandler<Void, UpdateRoutineCommand> {
  private final RoutineRepository routineRepository;
  private final SqlRoutineRepository sqlRoutineRepository;

  @Override
  @Transactional
  public Void handle(UpdateRoutineCommand command) {
    val routineCheck = sqlRoutineRepository
        .findWithUserIdById(command.getRoutineId())
        .orElseThrow(() -> new NotFoundException(Routine.class, command.getRoutineId()));

    if (!routineCheck.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    val routine = routineRepository
        .findById(command.getRoutineId())
        .orElseThrow(() -> new NotFoundException(Routine.class, command.getRoutineId()));

    routine.update(command.getWeekDay());
    routineRepository.save(routine);

    return null;
  }
}
