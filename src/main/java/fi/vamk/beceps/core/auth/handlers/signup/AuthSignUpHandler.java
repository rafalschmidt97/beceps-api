package fi.vamk.beceps.core.auth.handlers.signup;

import fi.vamk.beceps.accounts.Account;
import fi.vamk.beceps.accounts.AccountsRepository;
import fi.vamk.beceps.common.exceptions.ConflictException;
import fi.vamk.beceps.core.auth.provider.DatabaseAccountDetails;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AuthSignUpHandler {
  private final AccountsRepository accountsRepository;
  private final PasswordEncoder passwordEncoder;
  private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;

  public HttpResponse<AccessRefreshToken> handle(UsernamePasswordCredentials request) {
    if (accountsRepository.existsByEmail(request.getUsername())) {
      throw ConflictException.alreadyExists(Account.class, String.format("email=%s", request.getUsername()));
    }

    val account = new Account(request.getUsername(), passwordEncoder.encode(request.getPassword()));
    accountsRepository.save(account);

    val accessRefreshToken = accessRefreshTokenGenerator.generate(new DatabaseAccountDetails(account));

    if (accessRefreshToken.isPresent()) {
      return HttpResponse.ok(accessRefreshToken.get());
    }
    return HttpResponse.serverError();
  }
}
