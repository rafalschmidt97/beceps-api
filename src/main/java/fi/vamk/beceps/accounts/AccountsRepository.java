package fi.vamk.beceps.accounts;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {
  boolean existsByEmail(String email);
}

