package fi.vamk.beceps.common.bus.command;

import fi.vamk.beceps.common.bus.query.Query;

public interface CommandBus {
  <R, C extends Command<R>> R executeCommand(C command);

  <R, Q extends Query<R>> R executeQuery(Q query);
}
