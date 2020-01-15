package fi.vamk.beceps.core.auth.refresh;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Repository
public interface RefreshTokensRepository extends CrudRepository<RefreshToken, Long> {
  boolean existsByToken(String token);

  @Transactional
  void deleteByToken(String token);

  @Transactional
  void deleteAllByAccountId(Long accountId);
}

