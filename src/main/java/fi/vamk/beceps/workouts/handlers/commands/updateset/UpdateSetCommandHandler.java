package fi.vamk.beceps.workouts.handlers.commands.updateset;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.updateset.UpdateSetCommand;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.SetRepository;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class UpdateSetCommandHandler implements CommandHandler<Void, UpdateSetCommand> {
  private final SetRepository setRepository;

  @Override
  public Void handle(UpdateSetCommand command) {
    val setCheck = setRepository
        .findWithUserIdById(command.getSetId())
        .orElseThrow(() -> new NotFoundException(Set.class, command.getSetId()));

    if (!setCheck.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    val set = setRepository
        .findById(command.getSetId())
        .orElseThrow(() -> new NotFoundException(Set.class, command.getSetId()));

    set.update(command.getName(), command.getSetsAmount(), command.getRepsAmount());
    setRepository.update(set);

    return null;
  }
}
