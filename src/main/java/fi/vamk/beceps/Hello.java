package fi.vamk.beceps;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Hello {
  @Id
  @GeneratedValue
  private Long id;
  private String message;

  public Hello(String message) {
    this.message = message;
  }
}
