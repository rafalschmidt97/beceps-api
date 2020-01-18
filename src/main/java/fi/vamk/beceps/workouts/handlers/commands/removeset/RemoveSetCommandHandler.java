package fi.vamk.beceps.workouts.handlers.commands.removeset;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ForbiddenException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import fi.vamk.beceps.workouts.api.events.commands.removeset.RemoveSetCommand;
import fi.vamk.beceps.workouts.domain.Set;
import fi.vamk.beceps.workouts.infrastructure.persistence.SetRepository;
import fi.vamk.beceps.workouts.infrastructure.persistence.SqlSetRepository;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class RemoveSetCommandHandler implements CommandHandler<Void, RemoveSetCommand> {
  private final SetRepository setRepository;
  private final SqlSetRepository sqlSetRepository;

  @Override
  @Transactional
  public Void handle(RemoveSetCommand command) {
    val set = sqlSetRepository
        .findWithUserIdById(command.getSetId())
        .orElseThrow(() -> new NotFoundException(Set.class, command.getSetId()));

    if (!set.getUserId().equals(command.getUserId())) {
      throw new ForbiddenException();
    }

    setRepository.deleteById(set.getId());

    return null;
  }
}
