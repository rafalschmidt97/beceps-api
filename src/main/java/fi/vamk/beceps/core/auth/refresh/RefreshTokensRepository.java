package fi.vamk.beceps.core.auth.refresh;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;
import javax.transaction.Transactional;

@Repository
public interface RefreshTokensRepository extends CrudRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  @Transactional
  void deleteByToken(String token);

  @Transactional
  void deleteAllByAccountId(Long accountId);
}

