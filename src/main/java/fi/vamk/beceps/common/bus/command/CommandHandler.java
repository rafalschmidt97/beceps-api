package fi.vamk.beceps.common.bus.command;

public interface CommandHandler<R, C extends Command<R>> {
  R handle(C command);
}
