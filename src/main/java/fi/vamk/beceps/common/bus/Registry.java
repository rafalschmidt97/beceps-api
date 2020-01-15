package fi.vamk.beceps.common.bus;

import fi.vamk.beceps.common.bus.command.Command;
import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.bus.command.CommandProvider;
import fi.vamk.beceps.common.bus.query.Query;
import fi.vamk.beceps.common.bus.query.QueryHandler;
import fi.vamk.beceps.common.bus.query.QueryProvider;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.reflect.GenericTypeUtils;
import io.micronaut.inject.BeanDefinition;
import java.util.HashMap;
import java.util.Map;
import lombok.val;

@SuppressWarnings("unchecked")
public class Registry {

  private final Map<Class<? extends Command>, CommandProvider> commandProviderMap = new HashMap<>();
  private final Map<Class<? extends Query>, QueryProvider> queryProviderMap = new HashMap<>();

  public Registry(ApplicationContext applicationContext) {
    val commandHandlers = applicationContext.getBeanDefinitions(CommandHandler.class);
    commandHandlers.forEach(x -> registerCommand(applicationContext, x));

    val queryHandlers = applicationContext.getBeanDefinitions(QueryHandler.class);
    queryHandlers.forEach(x -> registerQuery(applicationContext, x));
  }

  private void registerCommand(ApplicationContext applicationContext, BeanDefinition<CommandHandler> bean) {
    val handlerClass = bean.getBeanType();
    val generics = GenericTypeUtils.resolveInterfaceTypeArguments(handlerClass, CommandHandler.class);
    val commandType = (Class<? extends Command>) generics[1];
    commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
  }

  private void registerQuery(ApplicationContext applicationContext, BeanDefinition<QueryHandler> bean) {
    val handlerClass = bean.getBeanType();
    val generics = GenericTypeUtils.resolveInterfaceTypeArguments(handlerClass, QueryHandler.class);
    val queryType = (Class<? extends Query>) generics[1];
    queryProviderMap.put(queryType, new QueryProvider(applicationContext, handlerClass));
  }

  public <R, C extends Command<R>> CommandHandler<R, C> getCommand(Class<C> commandClass) {
    return commandProviderMap.get(commandClass).get();
  }

  public <R, C extends Query<R>> QueryHandler<R, C> getQuery(Class<C> commandClass) {
    return queryProviderMap.get(commandClass).get();
  }
}
