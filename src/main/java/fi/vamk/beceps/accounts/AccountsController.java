package fi.vamk.beceps.accounts;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.RequiredArgsConstructor;

@Controller("accounts")
@RequiredArgsConstructor
public class AccountsController {
  private final AccountsService accountsService;

  @Get("{id}")
  public AccountDto findById(Long id) {
    return accountsService.findById(id);
  }
}
