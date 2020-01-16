package fi.vamk.beceps.common.bus.command;

import fi.vamk.beceps.common.bus.Registry;
import fi.vamk.beceps.common.bus.query.Query;
import fi.vamk.beceps.common.bus.query.QueryHandler;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

// The codebase is provided from asc-lab/micronaut-microservices-poc repository
@Singleton
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class MicronautCommandBus implements CommandBus {
  private final Registry registry;

  @Override
  public <R, C extends Command<R>> R executeCommand(C command) {
    val commandHandler = (CommandHandler<R, C>) registry.getCommand(command.getClass());
    return commandHandler.handle(command);
  }

  @Override
  public <R, Q extends Query<R>> R executeQuery(Q query) {
    val commandHandler = (QueryHandler<R, Q>) registry.getQuery(query.getClass());
    return commandHandler.handle(query);
  }
}
