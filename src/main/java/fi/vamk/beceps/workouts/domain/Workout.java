package fi.vamk.beceps.workouts.domain;

import fi.vamk.beceps.users.domain.User;
import java.time.Instant;
import java.util.List;
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
public class Workout {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false)
  private Instant createdAt;

  @Column
  private Instant modifiedAt;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Transient
  private User user;

  @Transient
  private List<Routine> routines;

  public Workout(String name, Long userId) {
    this.name = name.trim();
    this.userId = userId;
    this.createdAt = Instant.now();
  }

  public void update(String name) {
    this.name = name.trim();
    this.modifiedAt = Instant.now();
  }
}
