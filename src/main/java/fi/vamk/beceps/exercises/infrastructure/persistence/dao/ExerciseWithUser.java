package fi.vamk.beceps.exercises.infrastructure.persistence.dao;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class ExerciseWithUser {
  private Long id;
  private Long userId;
}
