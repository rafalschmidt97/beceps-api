package fi.vamk.beceps.accounts;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountDto {
  Long id;
  String email;

  AccountDto(Account account) {
    this(account.getId(), account.getEmail());
  }
}
