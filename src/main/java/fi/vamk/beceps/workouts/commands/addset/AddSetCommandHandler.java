package fi.vamk.beceps.workouts.commands.addset;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.addset.AddSetCommand;
import fi.vamk.beceps.workouts.domain.Routine;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.RoutineRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.SetRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AddSetCommandHandler implements CommandHandler<Void, AddSetCommand> {
  private final RoutineRepository routineRepository;
  private final SetRepository setRepository;

  @Override
  public Void handle(AddSetCommand command) {
    if (!routineRepository.existsById(command.getRoutineId())) {
      throw new NotFoundException(Routine.class, command.getRoutineId());
    }

    val set = new Set(command.getName(), command.getSetsAmount(), command.getRepsAmount(), command.getRoutineId());
    setRepository.insert(set);

    return null;
  }
}
