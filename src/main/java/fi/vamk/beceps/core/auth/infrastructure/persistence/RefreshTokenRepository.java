package fi.vamk.beceps.core.auth.infrastructure.persistence;

import fi.vamk.beceps.core.auth.domain.RefreshToken;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface RefreshTokenRepository extends GenericRepository<RefreshToken, Long> {
  boolean existsByToken(String token);

  void deleteByToken(String token);

  void deleteAllByUserId(Long userId);

  RefreshToken save(RefreshToken refreshToken);
}

