package fi.vamk.beceps.workouts.commands.removeset;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.removeset.RemoveSetCommand;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.SetRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveSetCommandHandler implements CommandHandler<Void, RemoveSetCommand> {
  private final SetRepository setRepository;

  @Override
  @Transactional
  public Void handle(RemoveSetCommand command) {
    val set = setRepository
        .findById(command.getSetId())
        .orElseThrow(() -> new NotFoundException(Set.class, command.getSetId()));

    if (!set.getRoutine().getWorkout().getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    setRepository.delete(set);

    return null;
  }
}
