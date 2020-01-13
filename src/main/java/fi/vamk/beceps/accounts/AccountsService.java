package fi.vamk.beceps.accounts;

import fi.vamk.beceps.common.exceptions.NotFoundException;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AccountsService {
  private final AccountsRepository accountsRepository;

  public AccountDto findById(Long id) {
    val account = accountsRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(Account.class, id));

    return new AccountDto(account);
  }
}
