package fi.vamk.beceps.workouts.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  private Date createdAt;

  @Column(name = "workout_id", nullable = false)
  private Long workoutId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workout_id", insertable = false, updatable = false)
  private Workout workout;

  @OneToMany(mappedBy = "routine", fetch = FetchType.LAZY)
  private List<Set> sets;

  public Routine(int weekDay, Long workoutId) {
    this.weekDay = weekDay;
    this.workoutId = workoutId;
    this.createdAt = new Date();
  }
}
