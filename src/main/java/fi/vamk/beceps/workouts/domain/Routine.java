package fi.vamk.beceps.workouts.domain;

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
public class Routine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private int weekDay;

  @Column(nullable = false)
  private Instant createdAt;

  @Column
  private Instant modifiedAt;

  @Column(name = "workout_id", nullable = false)
  private Long workoutId;

  @Transient
  private Workout workout;

  @Transient
  private List<Set> sets;

  public Routine(int weekDay, Long workoutId) {
    this.weekDay = weekDay;
    this.workoutId = workoutId;
    this.createdAt = Instant.now();
  }

  public void update(int weekDay) {
    this.weekDay = weekDay;
    this.modifiedAt = Instant.now();
  }
}
