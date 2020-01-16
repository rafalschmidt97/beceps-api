package fi.vamk.beceps.common.bus.query;

public interface QueryHandler<R, C extends Query<R>> {
  R handle(C query);
}
