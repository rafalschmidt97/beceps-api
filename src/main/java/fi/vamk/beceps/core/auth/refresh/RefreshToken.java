package fi.vamk.beceps.core.auth.refresh;

import fi.vamk.beceps.accounts.Account;
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
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date issuedAt;

  @Column(nullable = false, length = 512)
  private String token;

  @Column(name = "account_id", nullable = false)
  private Long accountId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", insertable = false, updatable = false)
  private Account account;

  public RefreshToken(String token, Long accountId) {
    this.issuedAt = new Date();
    this.token = token;
    this.accountId = accountId;
  }
}
