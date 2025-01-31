package fi.vamk.beceps.core.auth.domain;

import fi.vamk.beceps.users.domain.User;
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
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Instant issuedAt;

  @Column(nullable = false, length = 512)
  private String token;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Transient
  private User user;

  public RefreshToken(String token, Long userId) {
    this.issuedAt = Instant.now();
    this.token = token;
    this.userId = userId;
  }
}
