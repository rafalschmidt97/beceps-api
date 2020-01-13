package fi.vamk.beceps;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;

@Repository
interface HelloRepository extends CrudRepository<Hello, Long> {
  Optional<Hello> findByMessage(String message);
}

