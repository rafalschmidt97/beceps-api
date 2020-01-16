package fi.vamk.beceps.workouts.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Set {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 60)
  private String name;

  @Column(nullable = false)
  private int setsAmount;

  @Column(nullable = false)
  private int repsAmount;

  @Column(nullable = false)
  private Date createdAt;

  @Column
  private Date modifiedAt;

  @Column(name = "routine_id", nullable = false)
  private Long routineId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "routine_id", insertable = false, updatable = false)
  private Routine routine;

  public Set(String name, int setsAmount, int repsAmount) {
    this.name = name;
    this.setsAmount = setsAmount;
    this.repsAmount = repsAmount;
    this.createdAt = new Date();
  }

  public void update(String name, int setsAmount, int repsAmount) {
    this.name = name;
    this.setsAmount = setsAmount;
    this.repsAmount = repsAmount;
    this.modifiedAt = new Date();
  }
}
