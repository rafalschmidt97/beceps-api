package fi.vamk.beceps.users.domain;

import fi.vamk.beceps.common.exceptions.ConflictException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50, unique = true)
  private String email;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = false)
  private Date createdAt;

  @Column(nullable = false)
  private boolean isLocked;

  @Column
  private Date lockedAt;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
    this.createdAt = new Date();
  }

  @Override
  public String toString() {
    return String.format("User{id=%d, email=%s, createdAt=%s}", id, email, createdAt);
  }

  void lock() {
    if (isLocked) {
      throw new ConflictException(String.format("%s(%s) is already removed.", getClass().getSimpleName(), id));
    }

    isLocked = true;
    lockedAt = new Date();
  }
}
