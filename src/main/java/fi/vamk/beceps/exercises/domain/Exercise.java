package fi.vamk.beceps.exercises.domain;

import fi.vamk.beceps.users.domain.User;
import fi.vamk.beceps.workouts.domain.Set;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private int reps;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(name = "set_id", nullable = false)
  private Long setId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Transient
  private Set set;

  @Transient
  private User user;

  public Exercise(int reps, Long setId, Long userId) {
    this.reps = reps;
    this.setId = setId;
    this.userId = userId;
    this.createdAt = Instant.now();
  }
}
