package fi.vamk.beceps.common.jdbc;

import io.micronaut.core.util.functional.ThrowingFunction;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@JdbcRepository(dialect = Dialect.MYSQL)
@RequiredArgsConstructor
public class JdbcFetcher {
  private final JdbcOperations jdbcOperations;

  public <T> List<T> fetch(
      String query,
      ThrowingConsumer<PreparedStatement, SQLException> statements,
      ThrowingFunction<ResultSet, T, SQLException> mapper
  ) {
    return jdbcOperations.prepareStatement(query, jdbcStatement -> {
      statements.accept(jdbcStatement);

      val resultSet = jdbcStatement.executeQuery();
      val result = new ArrayList<T>();

      while (resultSet.next()) {
        result.add(mapper.apply(resultSet));
      }

      return result;
    });
  }

  public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T input) throws E;
  }
}
