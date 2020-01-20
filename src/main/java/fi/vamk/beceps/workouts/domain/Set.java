package fi.vamk.beceps.workouts.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`set`")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Set {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false)
  private int setsAmount;

  @Column(nullable = false)
  private int repsAmount;

  @Column(nullable = false)
  private Instant createdAt;

  @Column
  private Instant modifiedAt;

  @Column(name = "routine_id", nullable = false)
  private Long routineId;

  @Transient
  private Routine routine;

  public Set(String name, int setsAmount, int repsAmount, Long routineId) {
    this.name = name.trim();
    this.setsAmount = setsAmount;
    this.repsAmount = repsAmount;
    this.routineId = routineId;
    this.createdAt = Instant.now();
  }

  public void update(String name, int setsAmount, int repsAmount) {
    this.name = name.trim();
    this.setsAmount = setsAmount;
    this.repsAmount = repsAmount;
    this.modifiedAt = Instant.now();
  }
}
