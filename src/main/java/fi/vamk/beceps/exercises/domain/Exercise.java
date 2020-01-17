package fi.vamk.beceps.exercises.domain;

import fi.vamk.beceps.users.domain.User;
import fi.vamk.beceps.workouts.domain.Set;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  private Date createdAt;

  @Column(name = "set_id", nullable = false)
  private Long setId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "set_id", insertable = false, updatable = false)
  private Set set;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  public Exercise(int reps, Long setId, Long userId) {
    this.reps = reps;
    this.setId = setId;
    this.userId = userId;
    this.createdAt = new Date();
  }
}
