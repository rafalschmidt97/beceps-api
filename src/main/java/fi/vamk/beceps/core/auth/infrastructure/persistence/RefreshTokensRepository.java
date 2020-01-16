package fi.vamk.beceps.core.auth.infrastructure.persistence;

import fi.vamk.beceps.core.auth.domain.RefreshToken;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import javax.transaction.Transactional;

@Repository
public interface RefreshTokensRepository extends GenericRepository<RefreshToken, Long> {
  boolean existsByToken(String token);

  @Transactional
  void deleteByToken(String token);

  @Transactional
  void deleteAllByUserId(Long userId);

  RefreshToken insert(RefreshToken refreshToken);
}

