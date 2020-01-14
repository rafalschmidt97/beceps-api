package fi.vamk.beceps.accounts;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50, unique = true)
  private String email;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = false)
  private Date createdAt;

  public Account(String email, String password) {
    this.email = email;
    this.password = password;
    this.createdAt = new Date();
  }

  @Override
  public String toString() {
    return String.format("Account{id=%d, email=%s, createdAt=%s}", id, email, createdAt);
  }
}
