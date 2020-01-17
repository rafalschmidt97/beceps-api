package fi.vamk.beceps.workouts.infrastructure.persistence.dao;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class RoutineWithUser {
  private Long id;
  private Long userId;
}
