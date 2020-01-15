package fi.vamk.beceps.common.bus.query;

import io.micronaut.context.ApplicationContext;

public class QueryProvider<H extends QueryHandler<?, ?>> {

  private final ApplicationContext applicationContext;
  private final Class<H> type;

  public QueryProvider(ApplicationContext applicationContext, Class<H> type) {
    this.applicationContext = applicationContext;
    this.type = type;
  }

  public H get() {
    return applicationContext.getBean(type);
  }
}
