package fi.vamk.beceps.common.bus.command;

import io.micronaut.context.ApplicationContext;

public class CommandProvider<H extends CommandHandler<?, ?>> {

  private final ApplicationContext applicationContext;
  private final Class<H> type;

  public CommandProvider(ApplicationContext applicationContext, Class<H> type) {
    this.applicationContext = applicationContext;
    this.type = type;
  }

  public H get() {
    return applicationContext.getBean(type);
  }
}
